function createChart(data) {

	(function(d3) {
		'use strict';

		var height = 500;
		var width = height * 1.5;
		var donutWidth = 150;
		var radius = Math.min(width, height) / 2;
		var legendRectSize = 18;			//size of colored squares (legend)
		var legendSpacing = 4;					

		var dataReduced = new Array();
		
		prepareData(data, dataReduced);
		
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


//		var tooltip = d3.select('#chart')           
//		.append('div')                            
//		.attr('class', 'chart_tooltip');             

//		tooltip.append('div')                      
//		.attr('class', selectedText);                 

//		tooltip.append('div')                      
//		.attr('class', selectedNum);  


//		//if percentage neni v selecteNum, tak pridej procenta
//		tooltip.append('div')                      
//		.attr('class', 'percent');



//		d3.json(dataReduced, function(error, dataset) {
//		dataset.forEach(function(d) {	//d is item of data array
//		d.enabled = true;
//		});

		var path = svg.selectAll('path') 	
		.data(pie(dataReduced))				
		.enter()
		.append('path')
		.attr('d', arc)
		.attr('fill', function(d, i) { 
			return color(d.data.selectedText);
		})
//		.each(function(d) { this._current = d; });

//		path.on('mouseover', function(d) {				//mouseover event handler
//		var total = d3.sum(dataReduced.map(function(d) {
//		return (d.enabled) ? d.selectedNum : 0;
//		}));
//		var percent = Math.round(1000 * d.data.selectedNum / total) / 10;
//		tooltip.select('.selectedText').html(d.data.selectedText);
//		tooltip.select('.selectedNum').html(d.data.selectedNum); 
//		tooltip.select('.percent').html(percent + '%'); 
//		tooltip.style('display', 'block');
//		});

//		path.on('mouseout', function() {                           
//		tooltip.style('display', 'none');                       
//		}); 

		/* OPTIONAL 
    path.on('mousemove', function(d) {                          
      tooltip.style('top', (d3.event.pageY + 10) + 'px')          
        .style('left', (d3.event.pageX + 10) + 'px');            
    });                                                         
		 */

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

//		.on('click', function(selectedText) {
//		var rect = d3.select(this);
//		var enabled = true;
//		var totalEnabled = d3.sum(dataReduced.map(function(d) {
//		return (d.enabled) ? 1 : 0;
//		}));

//		if (rect.attr('class') === 'disabled') {
//		rect.attr('class', '');
//		} else {
//		if (totalEnabled < 2) return;
//		rect.attr('class', 'disabled');
//		enabled = false;
//		}

//		pie.value(function(d) {
//		if (d.selectedText === selectedText) d.enabled = enabled;
//		return (d.enabled) ? d.selectedNum : 0;
//		});

//		path = path.data(pie(dataReduced));

//		path.transition()
//		.duration(750)
//		.attrTween('d', function(d) {
//		var interpolate = d3.interpolate(this._current, d);
//		this._current = interpolate(0);
//		return function(t) {
//		return arc(interpolate(t));
//		};
//		});
//		});

		legend.append('text')
		.attr('x', legendRectSize + legendSpacing)
		.attr('y', legendRectSize - legendSpacing)
		.text(function(d) { return d; });


//		}); 

	})(window.d3);
}

function removeChart() {
	d3.select("svg")
	.remove();
}

function prepareData(data, dataReduced) {
	var selectedText = $('#chart-selector-text').val();
	var selectedNum = $('#chart-selector-number').val();

	var itemReduced;

	for (var aKey in data) {
		var kk = data[aKey];
		var objTemp = {};
		for (var dd in kk) {
			if (dd == selectedText){
//				var keyTemp = dd;
//				objTemp[keyTemp] = kk[dd];	
				objTemp["selectedText"] = kk[dd];	
			}	
			if (dd == selectedNum){
//				var keyTemp = dd;
//				objTemp[keyTemp] = kk[dd];	
				objTemp["selectedNum"] = kk[dd];
			}	
		}
		dataReduced.push(objTemp);
	}	
}