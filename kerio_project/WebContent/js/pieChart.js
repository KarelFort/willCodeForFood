/* Chart was created with help of http://zeroviscosity.com/d3-js-step-by-step/step-0-intro*/

function createChart(data) {

	(function(d3) {
		'use strict';

		var height = 500;
		var width = height * 1.6;
		var donutWidth = 150;
		var radius = Math.min(width, height) / 2;
		var legendRectSize = 18;			//size of colored squares (legend)
		var legendSpacing = 4;					

		var dataReduced = new Array();
		var selectedText = $('#chart-selector-text').val();
		var selectedNum = $('#chart-selector-number').val();

		prepareData(data, dataReduced, selectedText, selectedNum);

		var color = d3.scale.category20c();	//color scale

		var svg = d3.select('#chart')		//retrieve the DOM element
		.append('svg')						//append an svg element to the element we've selected
		.attr('width', width)
		.attr('height', height)
		.append('g')
		.attr('transform', 'translate(' + (width / 3) + 
				',' + (height / 2) + ')');

		var arc = d3.svg.arc()				//define radius, which determines the size of the overall chart
		.innerRadius(radius - donutWidth)  	//create donut hole
		.outerRadius(radius);

		var pie = d3.layout.pie()			//start and end angles of the segments
		.value(function(d) { 
			return d.selectedNum; })
			.sort(null);

		var tooltip = d3.select('#chart')           
		.append('div')                            
		.attr('class', 'chart-tooltip');             

		tooltip.append('div')                      
		.attr('class', 'selectedText');                 

		tooltip.append('div')                       
		.attr('class', 'selectedNum');  

		tooltip.append('div')                      
		.attr('class', 'percent');

		var path = svg.selectAll('path') 	
		.data(pie(dataReduced))				
		.enter()
		.append('path')
		.attr('d', arc)
		.attr('fill', function(d, i) { 
			return color(d.data.selectedText);
		})

		path.on('mouseover', function(d) {			
			var total = d3.sum(dataReduced.map(function(d) {
				return d.selectedNum;
			}));
			var percent = Math.round(1000 * d.data.selectedNum / total) / 10;
			tooltip.select('.selectedText').html(d.data.selectedText);
			tooltip.select('.selectedNum').html(d.data.selectedNum); 
			tooltip.select('.percent').html(percent + '%'); 
			tooltip.style('display', 'block');
		});

		path.on('mouseout', function() {                           
			tooltip.style('display', 'none');                       
		}); 

		var legend = svg.selectAll('#chart-legend')
		.data(color.domain())
		.enter()
		.append('g')
		.attr('class', 'chart-legend')
		.attr('transform', function(d, i) {
			var sqHeight = legendRectSize + legendSpacing;	//colored square
			var offset =  0	//height of square*number of records / 2
			var horz = height / 2 + 30;
			var vert = i * sqHeight - height / 2;
			return 'translate(' + horz + ',' + vert + ')'; 
		});

		legend.append('rect')
		.attr('width', legendRectSize)
		.attr('height', legendRectSize)
		.style('fill', color)
		.style('stroke', color)


		legend.append('text')
		.attr('x', legendRectSize + legendSpacing)
		.attr('y', legendRectSize - legendSpacing)
		.text(function(d) {

			for (var k in dataReduced) {
				var kk = dataReduced[k];
				var number;

				if (kk.selectedText == d) {
					number = kk.selectedNum
				}
			}

			var total = d3.sum(dataReduced.map(function(d) {
				return d.selectedNum;
			}));
			var percent = Math.round(1000 * number / total) / 10;

			return percent + " % | " + d  ;
		});

	})(window.d3);
}

function removeChart() {
	d3.select("svg")
	.remove();
}

function prepareData(data, dataReduced, selectedText, selectedNum) {

	var itemReduced;
	var aSelectedValues = new Array();

	//create ao of selectedText and selectedNum
	for (var aKey in data) {
		var objItem = data[aKey];
		var objTemp = {};
		for (var oKey in objItem) {
			if (oKey == selectedText){
				objTemp["selectedText"] = objItem[oKey];	
			}	
			if (oKey == selectedNum){
				objTemp["selectedNum"] = objItem[oKey];
			}	
		}
		aSelectedValues.push(objTemp);
	}	

	// sum same values
	var temp = {};
	var obj = null;
	for (var i = 0; i < aSelectedValues.length; i++) {
		obj = aSelectedValues[i];

		if(!temp[obj.selectedText]) {
			temp[obj.selectedText] = obj;
		} else {
			temp[obj.selectedText].selectedNum += obj.selectedNum;
		}
	}
	for (var prop in temp) {
		dataReduced.push(temp[prop]);
	}
}
