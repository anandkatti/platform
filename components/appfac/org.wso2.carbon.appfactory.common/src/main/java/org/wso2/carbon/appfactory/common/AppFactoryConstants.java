/*
 * Copyright 2005-2011 WSO2, Inc. (http://wso2.com)
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package org.wso2.carbon.appfactory.common;

import java.io.File;

/**
 * Constants for AppFactory configuration
 */
public class AppFactoryConstants {
    public static final String CONFIG_FOLDER = "appfactory";
    public static final String CONFIG_FILE_NAME = "appfactory.xml";
    public static final String CONFIG_NAMESPACE = "http://www.wso2.org/appfactory/";

    public static final String SERVER_ADMIN_NAME = "AdminUserName";
    public static final String SERVER_ADMIN_PASSWORD = "AdminPassword";
    public static final String DEPLOYMENT_STAGES = "ApplicationDeployment.DeploymentStage";
    public static final String DEPLOYMENT_URL = "DeploymentServerURL";
    public static final String APPFACTORY_WEB_CONTEXT_ROOT = "WebContextRoot";

    public static final String APPFACTORY_SERVER_URL = "ServerUrls.AppFactory";
    public static final String BPS_SERVER_URL = "ServerUrls.BPS";
    public static final String GREG_SERVER_URL = "ServerUrls.Greg";
    public static final String APP_OWNER_ROLE = "appOwner";

    /**
     *  The server URL of API Manager instance.
     */
    public static final String API_MANAGER_SERVICE_ENDPOINT = "ApiManager.Server.Url";
    public static final String API_MANAGER_SERVER_URL = "ServerUrls.ApiManager";

    public static final String SCM_ADMIN_NAME = "RepositoryProviderConfig.svn.Property.SCMServerAdminUserName";
    public static final String SCM_ADMIN_PASSWORD = "RepositoryProviderConfig.svn.Property.SCMServerAdminPassword";
    public static final String SCM_SERVER_URL = "RepositoryProviderConfig.svn.Property.BaseURL";
    public static final String SCM_READ_WRITE_ROLE = "RepositoryProviderConfig.svn.Property.ReadWriteRole";
    public static final String REPOSITORY_PROVIDER_CONFIG = "RepositoryProviderConfig";
    public static final String APPLICATION_TYPE_CONFIG = "ApplicationType";

    public static final String DEFAULT_APPLICATION_USER_ROLE = "DefaultUserRole";
    public static final String PERMISSION = "Permission";

    public static final String REGISTRY_GOVERNANCE_PATH = "/_system/governance";
    public static final String REGISTRY_APPLICATION_PATH = "/repository/applications";
    public static final String APPLICATION_ARTIFACT_NAME =  "appinfo";

    public static final String APPLICATION_ID = "applicationId";
    public static final String APPLICATION_REVISION = "revision";
    public static final String APPLICATION_VERSION = "version";
    public static final String APPLICATION_STAGE = "stage";
    public static final String APPLICATION_BUILD = "build";

    public static final String TRUNK = "trunk";
    public static final String BRANCH = "branches";
    public static final String TAG = "tags";

    public static final String FILE_TYPE_CAR = "car";
    public static final String FILE_TYPE_JAXWS = "jaxws";
    public static final String FILE_TYPE_JAXRS = "jaxrs";
    public static final String FILE_TYPE_WAR = "war";
    public static final String FILE_TYPE_JAGGERY = "jaggery";
    public static final String FILE_TYPE_DBS = "dbs";
    public static final String FILE_TYPE_BPEL = "bpel";
    public static final String FILE_TYPE_PHP = "php";
    public static final String FILE_TYPE_ESB = "esb";

    public static final String SCM_READ_WRITE_PERMISSION = "RepositoryProviderConfig.%s.Property.ReadWritePermission";

    /**
     * Defines the property name for maven archetype generation parameters
     */
    public static final String CAPP_MAVEN_ARCHETYPE_PROP_NAME = "MavenArchetype.CApp.Properties";
    public static final String WEBAPP_MAVEN_ARCHETYPE_PROP_NAME = "MavenArchetype.WebApp.Properties";
    public static final String JAX_WEBAPP_MAVEN_ARCHETYPE_PROP_NAME = "MavenArchetype.JAXWS.Properties";
    public static final String JAX_RS_WEBAPP_MAVEN_ARCHETYPE_PROP_NAME = "MavenArchetype.JAXRS.Properties";
    public static final String JAGGERY_APP_MAVEN_ARCHETYPE_PROP_NAME = "MavenArchetype.Jaggery.Properties";
    public static final String DBS_APP_MAVEN_ARCHETYPE_PROP_NAME = "MavenArchetype.DBS.Properties";
    public static final String ESB_APP_MAVEN_ARCHETYPE_PROP_NAME = "MavenArchetype.ESB.Properties";
    public static final String BPEL_MAVEN_ARCHETYPE_PROP_NAME = "MavenArchetype.BPEL.Properties";
    public static final String PHP_MAVEN_ARCHETYPE_PROP_NAME = "MavenArchetype.php.Properties";
    public static final String PREFERRED_REPOSITORY_TYPE = "RepositoryType";

    public static final String SECURE_VAULT_NS = "http://org.wso2.securevault/configuration";
    public static final String SECRET_ALIAS_ATTR_NAME = "secretAlias";
    public static final String REGISTRATION_LINK = "RegistrationLink";

    /**
     * External system names
     */
    public static final String REDMINE = "redmine";
    public static final String JENKINS = "jenkins";
    
    public static String[] JENKINS_MVN_PROJECT_TYPE = {FILE_TYPE_WAR,FILE_TYPE_CAR,FILE_TYPE_JAXRS,FILE_TYPE_JAXWS,FILE_TYPE_JAGGERY,FILE_TYPE_BPEL};

    public static String[] JENKINS_FREESTYLE_PROJECT_TYPE = {FILE_TYPE_DBS,FILE_TYPE_PHP,FILE_TYPE_ESB};


	//    constants added for Deployers
	public static final String APPLICATION_TYPE_WAR = "war";
    public static final String APPLICATION_TYPE_CAR = "car";
    public static final String APPLICATION_TYPE_ZIP = "zip";
    public static final String APPLICATION_TYPE_JAXWS = "jaxws";
    public static final String APPLICATION_TYPE_JAXRS = "jaxrs";
    public static final String APPLICATION_TYPE_JAGGERY = "jaggery";
    public static final String APPLICATION_TYPE_DBS = "dbs";
    public static final String APPLICATION_TYPE_PHP = "php";
    public static final String APPLICATION_TYPE_ESB = "esb";
    public static final String APPLICATION_TYPE_XML = "xml";
    public static final String APPLICATION_TYPE_BPEL = "bpel";


    public static final String JOB_NAME = "jobName";
    public static final String TAG_NAME = "tagName";
    public static final String DEPLOY_STAGE = "deployStage";
    public static final String ARTIFACT_TYPE = "artifactType";
    public static final String DEPLOYMENT_SERVER_URLS = "DeploymentServerURL";
    public static final String DEPLOY_ACTION = "deployAction";
    
    public static final String ESB_ARTIFACT_PREFIX = "synapse-config";
    public static final String ESB_ARTIFACT_DEPLOYMENT_PATH = "synapse-configs" + File.separator + "default";
    
    public static final String APP_VERSION_CACHE = "af.appversion.cache";
    public static final String APP_VERSION_CACHE_MANAGER = "af.appversion.cache.manager";
    public static final String APP_VERSION_STAGE_CACHE = "af.appversion.stage.cache";
    public static final String APP_VERSION_STAGE_CACHE_MANAGER = "af.appversion.stage.cache.manager";


	
	/**
	 * Enum to represent of different application stages.
	 */
	public enum ApplicationStage {

		DEVELOPMENT("development"), TEST("test"), STAGING("staging"), PRODUCTION("production"),
		RETIRED("retired");

		String stage = null;

		ApplicationStage(String strValue) {
			stage = strValue;
		}

		public String getStageStrValue() {
			return stage;
		}
		
	}
}
