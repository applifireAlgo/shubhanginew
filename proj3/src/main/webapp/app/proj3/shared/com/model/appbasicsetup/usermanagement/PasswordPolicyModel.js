Ext.define('Proj3.proj3.shared.com.model.appbasicsetup.usermanagement.PasswordPolicyModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "policyId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "policyName",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "policyDescription",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "maxPwdLength",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "minPwdLength",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "minCapitalLetters",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "minSmallLetters",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "minNumericValues",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "minSpecialLetters",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "allowedSpecialLetters",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});