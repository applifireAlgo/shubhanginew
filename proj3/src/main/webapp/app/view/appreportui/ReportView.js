Ext.define('Proj3.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Proj3.view.appreportui.ReportViewController',
	            'Proj3.view.appreportui.datagrid.DataGridPanel',
	            'Proj3.view.appreportui.datagrid.DataGridView',
	            'Proj3.view.appreportui.querycriteria.QueryCriteriaView',
	            'Proj3.view.appreportui.chart.ChartView',
	            'Proj3.view.appreportui.datapoint.DataPointView',
	            'Proj3.view.googlemaps.map.MapPanel',
	            'Proj3.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
