(function(d3) {
	'use strict';

	var width = 450;
	var height = 450;
	var donutWidth = 100;
	var radius = Math.min(width, height) / 2;
	var legendRectSize = 18;			//size of colored squares
	var legendSpacing = 4;					

	var color = d3.scale.category20c();	//color scale

	var svg = d3.select('#chart')		//retrieve the DOM element
	.append('svg')						//append an svg element to the element we've selected
	.attr('width', width)
	.attr('height', height)
	.append('g')
	.attr('transform', 'translate(' + (width / 2) + 
			',' + (height / 2) + ')');

	var arc = d3.svg.arc()				//define radius, which determines the size of the overall chart
	.innerRadius(radius - donutWidth)  	//create donut hole
	.outerRadius(radius);

	var pie = d3.layout.pie()			//start and end angles of the segments
	.value(function(d) { return d.count; })
	.sort(null);

	var tooltip = d3.select('#chart')           
	.append('div')                            
	.attr('class', 'chart_tooltip');             

	tooltip.append('div')                      
	.attr('class', 'device');                 

	tooltip.append('div')                      
	.attr('class', 'count');                

	tooltip.append('div')                      
	.attr('class', 'percent');    


	d3.json('data/chart_result.json', function(error, dataset) {
		dataset.forEach(function(d) {
			d.count = +d.count;
			d.enabled = true;
		});


		var path = svg.selectAll('path')
		.data(pie(dataset))
		.enter()
		.append('path')
		.attr('d', arc)
		.attr('fill', function(d, i) { 
			return color(d.data.device);
		})
		.each(function(d) { this._current = d; });

		path.on('mouseover', function(d) {				//mouseover event handler
			var total = d3.sum(dataset.map(function(d) {
				return (d.enabled) ? d.count : 0;
			}));
			var percent = Math.round(1000 * d.data.count / total) / 10;
			tooltip.select('.device').html(d.data.device);
			tooltip.select('.count').html(d.data.count); 
			tooltip.select('.percent').html(percent + '%'); 
			tooltip.style('display', 'block');
		});

		path.on('mouseout', function() {                              // NEW
			tooltip.style('display', 'none');                           // NEW
		}); 

		/* OPTIONAL 
    path.on('mousemove', function(d) {                            // NEW
      tooltip.style('top', (d3.event.pageY + 10) + 'px')          // NEW
        .style('left', (d3.event.pageX + 10) + 'px');             // NEW
    });                                                           // NEW
		 */

		var legend = svg.selectAll('.chart_legend')
		.data(color.domain())
		.enter()
		.append('g')
		.attr('class', 'chart_legend')
		.attr('transform', function(d, i) {
			var height = legendRectSize + legendSpacing;
			var offset =  height * color.domain().length / 2;
			var horz = -2 * legendRectSize;
			var vert = i * height - offset;
			return 'translate(' + horz + ',' + vert + ')';
		});

		legend.append('rect')
		.attr('width', legendRectSize)
		.attr('height', legendRectSize)
		.style('fill', color)
		.style('stroke', color)
		.on('click', function(device) {
			var rect = d3.select(this);
			var enabled = true;
			var totalEnabled = d3.sum(dataset.map(function(d) {
				return (d.enabled) ? 1 : 0;
			}));

			if (rect.attr('class') === 'disabled') {
				rect.attr('class', '');
			} else {
				if (totalEnabled < 2) return;
				rect.attr('class', 'disabled');
				enabled = false;
			}

			pie.value(function(d) {
				if (d.device === device) d.enabled = enabled;
				return (d.enabled) ? d.count : 0;
			});

			path = path.data(pie(dataset));

			path.transition()
			.duration(750)
			.attrTween('d', function(d) {
				var interpolate = d3.interpolate(this._current, d);
				this._current = interpolate(0);
				return function(t) {
					return arc(interpolate(t));
				};
			});
		});

		legend.append('text')
		.attr('x', legendRectSize + legendSpacing)
		.attr('y', legendRectSize - legendSpacing)
		.text(function(d) { return d; });


	}); 

})(window.d3);