/*
*  Copyright (c) 2013, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.carbon.inspectors.properties;

import org.apache.axis2.context.OperationContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * In synapse configuration you have to specify full qualified class name as the name for the class mediator as follows.
 * <class name="org.wso2.carbon.inspectors.properties.PropertyInspector"/>
 */
public class PropertyInspector extends AbstractMediator {

    private static final Log log = LogFactory.getLog(PropertyInspector.class);

    /**
     * Mediate the message.
     * <p/>
     * This is the execution point of the mediator.
     *
     * @param ctx MessageContext to be mediated
     * @return whether to continue the flow
     */
    public boolean mediate(MessageContext ctx) {
        log.info("================================ Synapse Property Inspector Start ================================");
        logSynapseContext(ctx);
        logAxisContext(ctx);
        logOperationContext(ctx);
        log.info("================================ Synapse Property Inspector End ================================");
        return true;
    }

    private void logSynapseContext(MessageContext ctx) {
        log.info("---------- Synapse Context Start ----------");
        Set properties = ctx.getPropertyKeySet();
        for (Object property : properties) {
            String name = (String) property;
            logProperty(name, ctx.getProperty(name));
        }
        log.info("---------- Synapse Context End ----------");
    }

    private void logAxisContext(MessageContext ctx) {
        log.info("---------- Axis2 Context Start ----------");
        org.apache.axis2.context.MessageContext context = ((Axis2MessageContext) ctx).getAxis2MessageContext();
        Map<String, Object> properties = context.getProperties();
        for (String name : properties.keySet()) {
            logProperty(name, properties.get(name));
        }
        log.info("---------- Axis2 Context End ----------");
    }

    private void logOperationContext(MessageContext ctx) {
        log.info("---------- Axis2 Operation Context Start ----------");
        OperationContext context = ((Axis2MessageContext) ctx).getAxis2MessageContext().getOperationContext();
        Iterator properties = context.getPropertyNames();
        while (properties.hasNext()) {
            String name = (String) properties.next();
            logProperty(name, context.getProperty(name));
        }
        log.info("---------- Axis2 Operation Context End ----------");
    }

    private void logProperty(String name, Object value) {
        log.info(name + " : " + value);
    }
}
