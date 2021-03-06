/*
*  Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.siddhi.test.management;

import com.hazelcast.core.Hazelcast;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.config.SiddhiConfiguration;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.exception.NoPersistenceStoreAssignedException;
import org.wso2.siddhi.core.persistence.InMemoryPersistenceStore;
import org.wso2.siddhi.core.persistence.PersistenceStore;
import org.wso2.siddhi.core.query.output.callback.QueryCallback;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.util.EventPrinter;
import org.wso2.siddhi.query.api.QueryFactory;
import org.wso2.siddhi.query.api.condition.Condition;
import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.definition.StreamDefinition;
import org.wso2.siddhi.query.api.expression.Expression;
import org.wso2.siddhi.query.api.query.Query;
import org.wso2.siddhi.query.api.query.input.pattern.Pattern;
import org.wso2.siddhi.query.compiler.exception.SiddhiPraserException;

public class PersistenceTestCase {
    static final Logger log = Logger.getLogger(PersistenceTestCase.class);

    private int count;
    private long lastValue;
    private long firstValue;
    private boolean eventArrived;

    @Before
    public void init() {
        lastValue = -1;
        firstValue = -1;
        count = 0;
        eventArrived = false;
    }

    @Test
    public void persistWindowTestQuery() throws InterruptedException, SiddhiPraserException {
        log.info("Persistence test on Window Query test1");

        PersistenceStore persistenceStore = new InMemoryPersistenceStore();
        String revision;

        String streamDefinition = "define stream cseStream ( symbol string, price float, volume int )";
        String query = "from cseStream[price>10]#window.length(10) " +
                       "select symbol, price, sum(volume) as totalVol " +
                       "insert into outStream ";
        QueryCallback callback = new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                Assert.assertTrue("IBM".equals(inEvents[0].getData(0)) || "WSO2".equals(inEvents[0].getData(0)));
                lastValue = (Long) inEvents[0].getData(2);
                count++;
                eventArrived = true;
            }
        };


        SiddhiConfiguration configuration = new SiddhiConfiguration();
        configuration.setQueryPlanIdentifier("Test");
        configuration.setAsyncProcessing(false);
        SiddhiManager siddhiManager = new SiddhiManager(configuration);
        siddhiManager.setPersistStore(persistenceStore);

        InputHandler inputHandler = siddhiManager.defineStream(streamDefinition);
        String queryReference1 = siddhiManager.addQuery(query);
        siddhiManager.addCallback(queryReference1, callback);

        inputHandler.send(new Object[]{"IBM", 75.6f, 100});
        inputHandler.send(new Object[]{"WSO2", 75.6f, 100});

        //persisting
        Thread.sleep(500);
        revision = siddhiManager.persist();

        inputHandler.send(new Object[]{"IBM", 75.6f, 100});
        inputHandler.send(new Object[]{"WSO2", 75.6f, 100});

        //restarting Siddhi
        Thread.sleep(500);
        siddhiManager.shutdown();
        configuration = new SiddhiConfiguration();
        configuration.setQueryPlanIdentifier("Test");
        siddhiManager = new SiddhiManager(configuration);
        siddhiManager.setPersistStore(persistenceStore);

        inputHandler = siddhiManager.defineStream(streamDefinition);
        String queryReference2 = siddhiManager.addQuery(query);
        siddhiManager.addCallback(queryReference2, callback);

        //loading
        siddhiManager.restoreLastRevision();

        inputHandler.send(new Object[]{"IBM", 75.6f, 100});
        inputHandler.send(new Object[]{"WSO2", 75.6f, 100});

        Thread.sleep(500);
        siddhiManager.shutdown();

        //Because of the use of in memory persistence store
        Hazelcast.shutdownAll();

        Assert.assertEquals(6, count);
        Assert.assertTrue(400 == lastValue);
        Assert.assertEquals(true, eventArrived);

    }

    @Test
    public void persistCountTestQuery() throws InterruptedException {
        log.info("Persist Count Test ");
        PersistenceStore persistenceStore = new InMemoryPersistenceStore();
        String revision;

        StreamDefinition streamDefinition1 = QueryFactory.createStreamDefinition().name("Stream1").attribute("symbol", Attribute.Type.STRING).attribute("price", Attribute.Type.FLOAT).attribute("volume", Attribute.Type.INT);
        StreamDefinition streamDefinition2 = QueryFactory.createStreamDefinition().name("Stream2").attribute("symbol", Attribute.Type.STRING).attribute("price", Attribute.Type.FLOAT).attribute("volume", Attribute.Type.INT);

        Query query = QueryFactory.createQuery();
        query.from(
                QueryFactory.patternStream(
                        Pattern.followedBy(
                                Pattern.count(
                                        QueryFactory.inputStream("e1", "Stream1").filter(
                                                Condition.compare(Expression.variable("price"),
                                                                  Condition.Operator.GREATER_THAN,
                                                                  Expression.value(20))), 2, 5),
                                QueryFactory.inputStream("e2", "Stream2").filter(
                                        Condition.compare(Expression.variable("price"),
                                                          Condition.Operator.GREATER_THAN,
                                                          Expression.value(20))))));

        query.select(
                QueryFactory.outputSelector().
                        select("price1.1", Expression.variable("e1", 0, "price")).
                        select("price1.2", Expression.variable("e1", 1, "price")).
                        select("price1.3", Expression.variable("e1", 2, "price")).
                        select("price1.4", Expression.variable("e1", 3, "price")).
                        select("price2", Expression.variable("e2", "price"))

        );
        query.insertInto("OutStream");

        QueryCallback callback = new QueryCallback() {
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                org.junit.Assert.assertArrayEquals(new Object[]{25.6f, 47.6f, null, null, 45.7f}, inEvents[0].getData());
                count++;
                eventArrived = true;
            }

        };

        SiddhiConfiguration configuration = new SiddhiConfiguration();
        configuration.setQueryPlanIdentifier("Test");
        SiddhiManager siddhiManager = new SiddhiManager(configuration);
        siddhiManager.setPersistStore(persistenceStore);

        InputHandler stream1 = siddhiManager.defineStream(streamDefinition1);
        InputHandler stream2 = siddhiManager.defineStream(streamDefinition2);

        String queryReference1 = siddhiManager.addQuery(query);

        siddhiManager.addCallback(queryReference1, callback);

        stream1.send(new Object[]{"WSO2", 25.6f, 100});
        Thread.sleep(500);
        stream1.send(new Object[]{"GOOG", 47.6f, 100});
        Thread.sleep(500);
        stream1.send(new Object[]{"GOOG", 13.7f, 100});
        Thread.sleep(1000);

        //persisting
        revision = siddhiManager.persist();
        Thread.sleep(1000);

        //Restarting siddhi
        siddhiManager.shutdown();
        configuration = new SiddhiConfiguration();
        configuration.setQueryPlanIdentifier("Test");
        siddhiManager = new SiddhiManager(configuration);
        siddhiManager.setPersistStore(persistenceStore);

        stream1 = siddhiManager.defineStream(streamDefinition1);
        stream2 = siddhiManager.defineStream(streamDefinition2);
        String queryReference2 = siddhiManager.addQuery(query);
        siddhiManager.addCallback(queryReference2, callback);

        //loading
        siddhiManager.restoreLastRevision();
        Thread.sleep(1000);

        stream2.send(new Object[]{"IBM", 45.7f, 100});
        Thread.sleep(500);
        stream1.send(new Object[]{"GOOG", 47.8f, 100});
        Thread.sleep(500);
        stream2.send(new Object[]{"IBM", 55.7f, 100});
        Thread.sleep(500);

        siddhiManager.shutdown();
        //Because of the use of in memory persistence store
        Hazelcast.shutdownAll();

        Assert.assertEquals(1, count);
        Assert.assertEquals(true, eventArrived);

    }

    @Test(expected = NoPersistenceStoreAssignedException.class)
    public void persistPatternTQuery() throws InterruptedException {
        log.info("No store defined case ");
//        PersistenceStore persistenceStore = new InMemoryPersistenceStore();
//        String revision;

        StreamDefinition streamDefinition1 = QueryFactory.createStreamDefinition().name("Stream1").attribute("symbol", Attribute.Type.STRING).attribute("price", Attribute.Type.FLOAT).attribute("volume", Attribute.Type.INT);
        StreamDefinition streamDefinition2 = QueryFactory.createStreamDefinition().name("Stream2").attribute("symbol", Attribute.Type.STRING).attribute("price", Attribute.Type.FLOAT).attribute("volume", Attribute.Type.INT);

        Query query = QueryFactory.createQuery();
        query.from(
                QueryFactory.patternStream(
                        Pattern.followedBy(
                                Pattern.count(
                                        QueryFactory.inputStream("e1", "Stream1").filter(
                                                Condition.compare(Expression.variable("price"),
                                                                  Condition.Operator.GREATER_THAN,
                                                                  Expression.value(20))), 2, 5),
                                QueryFactory.inputStream("e2", "Stream2").filter(
                                        Condition.compare(Expression.variable("price"),
                                                          Condition.Operator.GREATER_THAN,
                                                          Expression.value(20))))));

        query.select(
                QueryFactory.outputSelector().
                        select("price1.1", Expression.variable("e1", 0, "price")).
                        select("price1.2", Expression.variable("e1", 1, "price")).
                        select("price1.3", Expression.variable("e1", 2, "price")).
                        select("price1.4", Expression.variable("e1", 3, "price")).
                        select("price2", Expression.variable("e2", "price"))

        );
        query.insertInto("OutStream");

        QueryCallback callback = new QueryCallback() {
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                org.junit.Assert.assertArrayEquals(new Object[]{25.6f, 47.6f, null, null, 45.7f}, inEvents[0].getData());
                count++;
                eventArrived = true;
            }

        };

        SiddhiManager siddhiManager = new SiddhiManager();
//        siddhiManager.setPersistStore(persistenceStore);

        InputHandler stream1 = siddhiManager.defineStream(streamDefinition1);
        InputHandler stream2 = siddhiManager.defineStream(streamDefinition2);

        String queryReference = siddhiManager.addQuery(query);

        siddhiManager.addCallback(queryReference, callback);

        stream1.send(new Object[]{"WSO2", 25.6f, 100});
        Thread.sleep(500);
        stream1.send(new Object[]{"GOOG", 47.6f, 100});
        Thread.sleep(500);
        stream1.send(new Object[]{"GOOG", 13.7f, 100});
        Thread.sleep(1000);

        //persisting
        siddhiManager.persist();
        Thread.sleep(1000);

        siddhiManager.shutdown();
        //Because of the use of in memory persistence store
        Hazelcast.shutdownAll();

    }


    @Test
    public void persistWindowRestartTestQuery() throws InterruptedException, SiddhiPraserException {
        log.info("Persistence test on Restart of Window");

        PersistenceStore persistenceStore = new InMemoryPersistenceStore();
        String revision;

        String streamDefinition = "define stream cseStream ( symbol string, price float, volume int )";
        String query = "from cseStream[price>10]#window.time(10000) " +
                       "select symbol, price, sum(volume) as totalVol " +
                       "insert into outStream";
        QueryCallback callback = new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
//                Assert.assertTrue("IBM".equals(inEvents[0].getData(0)) || "WSO2".equals(inEvents[0].getData(0)));
                lastValue = (Long) inEvents[0].getData(2);
                count++;
                eventArrived = true;
            }
        };


        SiddhiConfiguration configuration = new SiddhiConfiguration();
        configuration.setQueryPlanIdentifier("Test");
        SiddhiManager siddhiManager = new SiddhiManager(configuration);
        siddhiManager.setPersistStore(persistenceStore);

        InputHandler inputHandler = siddhiManager.defineStream(streamDefinition);
        String queryReference1 = siddhiManager.addQuery(query);
        siddhiManager.addCallback(queryReference1, callback);

        inputHandler.send(new Object[]{"IBM", 75.6f, 100});
        inputHandler.send(new Object[]{"WSO2", 76.6f, 100});

        //persisting
        revision = siddhiManager.persist();

        inputHandler.send(new Object[]{"IBM", 77.6f, 100});
        inputHandler.send(new Object[]{"WSO2", 78.6f, 100});

        //restarting Siddhi
        siddhiManager.shutdown();
        configuration = new SiddhiConfiguration();
        configuration.setQueryPlanIdentifier("Test");
        siddhiManager = new SiddhiManager(configuration);
        siddhiManager.setPersistStore(persistenceStore);

        inputHandler = siddhiManager.defineStream(streamDefinition);
        String queryReference2 = siddhiManager.addQuery(query);
        siddhiManager.addCallback(queryReference2, callback);

        //loading
        siddhiManager.restoreLastRevision();


        Thread.sleep(1000);
        siddhiManager.shutdown();
        //Because of the use of in memory persistence store
        Hazelcast.shutdownAll();

        Assert.assertEquals(4, count);
        Assert.assertEquals(400, lastValue);
        Assert.assertEquals(true, eventArrived);

    }

    @Test
    public void persistDistributedWindowRestartTestQuery()
            throws InterruptedException, SiddhiPraserException {
        log.info("Persistence test on Restart of Window query");

        PersistenceStore persistenceStore = new InMemoryPersistenceStore();
        String revision;

        String streamDefinition = "define stream cseStream ( symbol string, price float, volume int )";
        String query = "from cseStream[price>10]#window.time(10000) " +
                       "select symbol, price, sum(volume) as totalVol " +
                       "insert into outStream for all-events ";
        QueryCallback callback = new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
//                Assert.assertTrue("IBM".equals(inEvents[0].getData(0)) || "WSO2".equals(inEvents[0].getData(0)));
                if (removeEvents != null) {
                    lastValue = (Long) removeEvents[removeEvents.length - 1].getData(2);
                } else {
                    firstValue = (Long) inEvents[inEvents.length - 1].getData(2);
                }
                count++;
                eventArrived = true;
            }
        };


        SiddhiConfiguration configuration = new SiddhiConfiguration();
        configuration.setQueryPlanIdentifier("Test");
        configuration.setDistributedProcessing(true);
        SiddhiManager siddhiManager = new SiddhiManager(configuration);
        siddhiManager.setPersistStore(persistenceStore);

        InputHandler inputHandler = siddhiManager.defineStream(streamDefinition);
        String queryReference1 = siddhiManager.addQuery(query);
        siddhiManager.addCallback(queryReference1, callback);

        inputHandler.send(new Object[]{"IBM", 75.6f, 100});
        inputHandler.send(new Object[]{"WSO2", 76.6f, 100});

        //persisting
        Thread.sleep(1000);
        revision = siddhiManager.persist();

        inputHandler.send(new Object[]{"IBM", 77.6f, 100});
        inputHandler.send(new Object[]{"WSO2", 78.6f, 100});

        //restarting Siddhi
        Thread.sleep(1000);
        siddhiManager.shutdown();
        configuration = new SiddhiConfiguration();
        configuration.setQueryPlanIdentifier("Test");
        configuration.setDistributedProcessing(true);
        siddhiManager = new SiddhiManager(configuration);
        siddhiManager.setPersistStore(persistenceStore);

        inputHandler = siddhiManager.defineStream(streamDefinition);
        String queryReference2 = siddhiManager.addQuery(query);
        siddhiManager.addCallback(queryReference2, callback);

        //loading
        siddhiManager.restoreLastRevision();


        Thread.sleep(15000);
        siddhiManager.shutdown();
        //Because of the use of in memory persistence store
        Hazelcast.shutdownAll();

        Assert.assertTrue(count >= 3);
        Assert.assertEquals(0, lastValue);
        Assert.assertEquals(400, firstValue);
        Assert.assertEquals(true, eventArrived);

    }


}
