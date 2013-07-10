/*
*Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*WSO2 Inc. licenses this file to you under the Apache License,
*Version 2.0 (the "License"); you may not use this file except
*in compliance with the License.
*You may obtain a copy of the License at
*
*http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing,
*software distributed under the License is distributed on an
*"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*KIND, either express or implied.  See the License for the
*specific language governing permissions and limitations
*under the License.
*/

package org.wso2.carbon.esb.car.deployment.test;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.commons.lang.ArrayUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.carbon.automation.api.clients.application.mgt.ApplicationAdminClient;
import org.wso2.carbon.automation.api.clients.application.mgt.CarbonAppUploaderClient;
import org.wso2.carbon.automation.api.clients.registry.ResourceAdminServiceClient;
import org.wso2.carbon.automation.core.annotations.ExecutionEnvironment;
import org.wso2.carbon.automation.core.annotations.SetEnvironment;
import org.wso2.carbon.esb.ESBIntegrationTest;
import org.wso2.carbon.registry.resource.stub.ResourceAdminServiceExceptionException;
import org.wso2.carbon.registry.resource.stub.common.xsd.ResourceData;

import javax.activation.DataHandler;
import java.io.File;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class XSLTTransformationCarTestCase extends ESBIntegrationTest {
    private CarbonAppUploaderClient carbonAppUploaderClient;
    private ApplicationAdminClient applicationAdminClient;
    private final int MAX_TIME = 120000;
    private final String carFileName = "xslt-transformation-car";
    private ResourceAdminServiceClient resourceAdminServiceStub;

    @BeforeClass(alwaysRun = true)
    protected void uploadCarFileTest() throws Exception {
        super.init();
        carbonAppUploaderClient = new CarbonAppUploaderClient(esbServer.getBackEndUrl(), esbServer.getSessionCookie());
        carbonAppUploaderClient.uploadCarbonAppArtifact("xslt-transformation-car_1.0.0.car"
                , new DataHandler(new URL("file:" + File.separator + File.separator + getESBResourceLocation()
                                          + File.separator + "car" + File.separator + "xslt-transformation-car_1.0.0.car")));
        applicationAdminClient = new ApplicationAdminClient(esbServer.getBackEndUrl(), esbServer.getSessionCookie());
        Assert.assertTrue(isCarFileDeployed(carFileName));
        TimeUnit.SECONDS.sleep(5);
        resourceAdminServiceStub =
                new ResourceAdminServiceClient(esbServer.getBackEndUrl(), esbServer.getSessionCookie());

    }

    @Test(groups = {"wso2.esb"}, description = "test endpoint deployment from car file")
    public void artifactDeployment() throws Exception {
        Assert.assertTrue(esbUtils.isEndpointDeployed(esbServer.getBackEndUrl(), esbServer.getSessionCookie(), "stockQuoteServiceEndpoint")
                , "AddressEndpoint Endpoint deployment failed");
        Assert.assertTrue(esbUtils.isProxyDeployed(esbServer.getBackEndUrl(), esbServer.getSessionCookie(), "xsltTransformationProxy")
                , "Pass Through Proxy service deployment failed");
        Assert.assertTrue(isResourceExist("/_system/config/transform.xslt"), "transform.xslt not found on registry");
        Assert.assertTrue(isResourceExist("/_system/config/transform_back.xslt"), "transform.xslt not found on registry");

    }

    @SetEnvironment(executionEnvironments = {ExecutionEnvironment.integration_all})
    @Test(groups = {"wso2.esb"}, description = "test proxy service invocation"
            , dependsOnMethods = {"artifactDeployment"})
    public void invokeTransformProxyService() throws Exception {
        OMElement response = axis2Client.sendCustomQuoteRequest(
                getProxyServiceURL("xsltTransformationProxy"),
                null,
                "XSLTTransformation");
        Assert.assertNotNull(response, "Response message null");
        Assert.assertTrue(response.toString().contains("Code"), "Code element not found in response message");
        Assert.assertTrue(response.toString().contains("XSLTTransformation"), "Symbol not found on the response message");
    }


    @AfterClass(alwaysRun = true)
    public void deleteCarFileAndArtifactUnDeploymentTest() throws Exception {
        applicationAdminClient.deleteApplication(carFileName);
        Assert.assertTrue(isCarFileUnDeployed(carFileName));
        TimeUnit.SECONDS.sleep(5);
        Assert.assertTrue(esbUtils.isEndpointUnDeployed(esbServer.getBackEndUrl(), esbServer.getSessionCookie(), "stockQuoteServiceEndpoint")
                , "stockQuoteServiceEndpoint Endpoint deployment failed");
        Assert.assertTrue(esbUtils.isProxyUnDeployed(esbServer.getBackEndUrl(), esbServer.getSessionCookie(), "xsltTransformationProxy")
                , "xsltTransformationProxy Proxy service deployment failed");

        Assert.assertFalse(isResourceExist("/_system/config/transform.xslt"), "transform.xslt not removed on registry");
        Assert.assertFalse(isResourceExist("/_system/config/transform_back.xslt"), "transform.xslt not removed on registry");
        super.cleanup();
    }

    private boolean isCarFileDeployed(String carFileName) throws Exception {

        log.info("waiting " + "180000" + " millis for car deployment " + carFileName);
        boolean isCarFileDeployed = false;
        Calendar startTime = Calendar.getInstance();
        long time;
        while ((time = (Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis())) < MAX_TIME) {
            String[] applicationList = applicationAdminClient.listAllApplications();
            if (applicationList != null) {
                if (ArrayUtils.contains(applicationList, carFileName)) {
                    isCarFileDeployed = true;
                    log.info("car file deployed in " + time + " mills");
                    return isCarFileDeployed;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //ignore
            }

        }
        return isCarFileDeployed;
    }

    private boolean isCarFileUnDeployed(String carFileName) throws Exception {

        log.info("waiting " + "180000" + " millis for car undeployment " + carFileName);
        boolean isCarFileUnDeployed = false;
        Calendar startTime = Calendar.getInstance();
        long time;
        while ((time = (Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis())) < MAX_TIME) {
            String[] applicationList = applicationAdminClient.listAllApplications();
            if (applicationList != null) {
                if (!ArrayUtils.contains(applicationList, carFileName)) {
                    isCarFileUnDeployed = true;
                    log.info("car file deployed in " + time + " mills");
                    return isCarFileUnDeployed;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //ignore
                }
            } else {
                isCarFileUnDeployed = true;
                log.info("car file deployed in " + time + " mills");
                return isCarFileUnDeployed;
            }


        }
        return isCarFileUnDeployed;
    }

    private boolean isResourceExist(String path) throws AxisFault {

        try {
            ResourceData[] resource = resourceAdminServiceStub.getResource(path);
            if (resource != null && resource.length > 0) {
                return true;
            } else {
                return false;
            }
        } catch (ResourceAdminServiceExceptionException e) {
            return false;
        } catch (RemoteException e) {
            return false;
        }

    }
}
