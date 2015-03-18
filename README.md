# esb-property-inspector

This class mediator will allow you to dump properties in syanpse, axis2, operation context of WSO2 ESB.

1. Copy libs/ into <ESB>/repository/components/lib directory
2. Add following class mediator into the place where you need dump the properties
	`<class name="org.wso2.carbon.inspectors.properties.PropertyInspector"/>`
3. Restart the server

Note: This might sometimes gives you an issue with PassThrough transport when it is being used in the out sequence.
