var mChart = null;

function chartExtender() {
	var config = $.extend(true, {}, this.cfg.config);

    config = {
		options: {
			"animation": {
				"delay": 0,
				"onComplete": function () {
					mChart = this;
					var ctx = this.ctx;
					ctx.textAlign = 'center';
					ctx.textBaseline = 'bottom';

					this.data.datasets.forEach(function (dataset, i) {
						var meta = mChart.getDatasetMeta(i);
						meta.data.forEach(function (bar, index) {
							var data = dataset.data[index];                            
							ctx.fillText(data, bar.x, bar.y - 5);
						});
					});
				}
			}
		}
	};
	
	$.extend(true, this.cfg.config, config);
};
