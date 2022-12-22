var labels = [];

function customLabel(context) {
	return labels[context.index] || '';
}

function chartExtender() {
	var config = $.extend(true, {}, this.cfg.config);
	labels = config.data.labels;
        
    config = {
		options: {
			tooltips: {
				callbacks: {
		        	label: customLabel
				}
			}
		}
	};
	
	$.extend(true, this.cfg.config, config);
};
