/**
 * TEMPLATE
 * jqGridDemo.js
 * 
 * VERSION
 * 0.1 (12.19.2011)
 * 
 * AUTHOR
 * Steve 'Cutter' Blades, webDOTadminATcutterscrossing.com
 * 
 * LICENSE
 * This document is licensed as free software under the terms of the
 * MIT License: http://www.opensource.org/licenses/mit-license.php
 * 
 * PURPOSE
 * This is the sample script for the first entry in the Cutter's Crossing
 * "Intro to jqGrid" posts, creating a small demo of using jqGrid
 */

var gridCols = {set:false};

$(document).ready(function(){
	var grid = $('#gridTest'); // JQuery object reference of table used for our grid display
	
	/*
	 * FUNCTION populateGrid
	 * Used as the 'datatype' attribute of the jqGrid config object, this method
	 * is used to handle the ajax calls and data manipulation needed to populate
	 * data within our jqGrid instance.
	 * @postdata (object) - this is the object passed as the 'postData' attribute
	 * 						of our jqGrid instance. 
	 */
	var populateGrid = function (postdata) {
		
	};
	
	grid.jqGrid({
	      url: "getAllExpensesJSON",
	      datatype: 'json',
	      mtype: 'GET',
	         colNames:['Id', 'AMOUNT', 'EXPENSE DATE', 'DESCRIPTION'],
	         colModel:[
	          {name:'id',index:'id', width:55,editable:false,editoptions:{readonly:true,size:10},hidden:true},
	          {name:'amount',index:'amount', width:100,editable:true, editrules:{required:true}, editoptions:{size:10}},
	          {name:'expenseDate',index:'expenseDate', width:100,editable:true, editrules:{required:true}, editoptions:{size:10}},
	          {name:'description',index:'description', width:100,editable:true, editrules:{required:true}, editoptions:{size:10}}
	         ],
	         postData: {
	      },
	      rowNum:20,
	         rowList:[20,40,60],
	         height: 200,
	         autowidth: true,
	      rownumbers: true,
	         pager: '#pager',
	         sortname: 'id',
	         viewrecords: true,
	         sortorder: "asc",
	         caption:"Users",
	         emptyrecords: "Empty records",
	         loadonce: false,
	         loadComplete: function() {
	      },
	         jsonReader : {
	             root: "rows",
	             page: "page",
	             total: "total",
	             records: "records",
	             repeatitems: false,
	             cell: "cell",
	             id: "id"
	         }
	     });
});