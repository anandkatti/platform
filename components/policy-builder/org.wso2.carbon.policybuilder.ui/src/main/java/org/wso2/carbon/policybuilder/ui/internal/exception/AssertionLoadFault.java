/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.carbon.policybuilder.ui.internal.exception;

/**
 * Created by IntelliJ IDEA.
 * User: usw
 * Date: Jan 30, 2009
 * Time: 12:10:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssertionLoadFault extends Exception {

	public AssertionLoadFault() {
		super("Assertion Load Error");
	}
}
