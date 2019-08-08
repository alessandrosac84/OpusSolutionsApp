

function loadCharts() {

	setInterval(function(){

		var indicadores;
		var dataIndicadores;

		var evolutionJson = $.getJSON("json/evolution.json", function(dataJson) {

			var dataEvolution = JSON.parse(evolutionJson.responseText);

			var labels = [];
			var data = [];

			for(var i = 0; i < dataEvolution.length; i++){
				labels.push(dataEvolution[i].mes)
			}

			for(var i = 0; i < dataEvolution.length; i++){
				data.push(dataEvolution[i].totalMes)
			}

			var ctx = lineChart.getContext('2d');
			var config = {
				type: 'line',
				data: {
					labels: labels,
					datasets: [{
						label: 'Crescimento Mensal de Seguros Opus 2019',
						data: data,
						backgroundColor: 'rgba(0, 119, 204, 0.3)'
					}]
				}
			};

			var chart = new Chart(ctx, config);


//     console.log(dataTeste)
//       console.log(mydata);
		});

		var seguroPorSeguradoraJson = $.getJSON("json/seguroPorSeguradora.json", function(dataJson) {

			var dataSeguroPorSeguradora = JSON.parse(seguroPorSeguradoraJson.responseText);

			var labels = [];
			var data = [];
			var background = [];

			for(var i = 0; i < dataSeguroPorSeguradora.length; i++){
				labels.push(dataSeguroPorSeguradora[i].name)
			}

			for(var i = 0; i < dataSeguroPorSeguradora.length; i++){
				data.push(dataSeguroPorSeguradora[i].value)
			}

			for(var i = 0; i < dataSeguroPorSeguradora.length; i++){
				background.push(dataSeguroPorSeguradora[i].background)
			}

			var ctx = barchartgrouped.getContext('2d');
			var config = {
				type: 'bar',
				data: {
					labels: labels,
					datasets: [{
						label: 'Total de Seguros por Seguradora',
						data: data,
						backgroundColor: background
					}],
					options: {
						title: {
							display: true,
							text: 'Total de Seguros por Seguradora'
						}
					}
				}
			};
			var chart = new Chart(ctx, config);
//	     console.log(dataTeste)
//	       console.log(mydata);
		});

//Indicadores

		Chart.pluginService.register({
			beforeDraw: function (chart) {
				if (chart.config.options.elements.center) {
					//Get ctx from string
					var ctx = chart.chart.ctx;

					//Get options from the center object in options
					var centerConfig = chart.config.options.elements.center;
					var fontStyle = centerConfig.fontStyle || 'Arial';
					var txt = centerConfig.text;
					var color = centerConfig.color || '#000';
					var sidePadding = centerConfig.sidePadding || 20;
					var sidePaddingCalculated = (sidePadding/100) * (chart.innerRadius * 2)
					//Start with a base font of 30px
					ctx.font = "30px " + fontStyle;

					//Get the width of the string and also the width of the element minus 10 to give it 5px side padding
					var stringWidth = ctx.measureText(txt).width;
					var elementWidth = (chart.innerRadius * 2) - sidePaddingCalculated;

					// Find out how much the font can grow in width.
					var widthRatio = elementWidth / stringWidth;
					var newFontSize = Math.floor(30 * widthRatio);
					var elementHeight = (chart.innerRadius * 2);

					// Pick a new font size so it will not be larger than the height of label.
					var fontSizeToUse = Math.min(newFontSize, elementHeight);

					//Set font settings to draw it correctly.
					ctx.textAlign = 'center';
					ctx.textBaseline = 'middle';
					var centerX = ((chart.chartArea.left + chart.chartArea.right) / 2);
					var centerY = ((chart.chartArea.top + chart.chartArea.bottom) / 2);
					ctx.font = fontSizeToUse+"px " + fontStyle;
					ctx.fillStyle = color;

					//Draw text in center
					ctx.fillText(txt, centerX, centerY);
				}
			}
		});

		indicadores = $.getJSON("json/indicadores.json", function(dataJsonIndic) {

			dataIndicadores = JSON.parse(indicadores.responseText);
			console.log(dataIndicadores)

			var progressVendas;
			var progressOrcamentos;
			var progressLigacoes;
			var progressConversao;

			var collorVendas;
			var collorOrcamentos;
			var collorLigacoes;
			var collorConversao;

			for(var i = 0; i < dataIndicadores.length; i++){

				if(dataIndicadores[i].name == 'Vendas'){
					progressVendas = dataIndicadores[i].valor;
					console.log('VENDAS')
					console.log(dataIndicadores[i].name)
					console.log(dataIndicadores[i].valor)
					// var progressVendas = 72;

					if(progressVendas == 100){
						collorVendas = [
							"#0fba00",
							"#ffffff",
						]
					}

					if(progressVendas > 75 && progressVendas < 99){
						collorVendas = [
							"#ffe900",
							"#ffffff",
						]
					}

					if(progressVendas < 75 && progressVendas > 50){
						collorVendas = [
							"#ffc4c4",
							"#ffffff",
						]
					}

					if(progressVendas < 50){
						collorVendas = [
							"#ff0000",
							"#ffffff",
						]
					}
					var configVendas = {
						type: 'doughnut',
						data: {
							labels: [
								"Atingido",
								"Não Atingido",
							],
							datasets: [{
								data: [progressVendas, 100 - progressVendas],
								backgroundColor: collorVendas,
								hoverBackgroundColor: collorVendas
							}]
						},
						options: {
							elements: {
								center: {
									text: progressVendas + '%',
									color: '#050505', // Default is #000000
									fontStyle: 'Arial', // Default is Arial
									sidePadding: 20 // Defualt is 20 (as a percentage)
								}
							}
						}
					};
				}

				if(dataIndicadores[i].name == 'Orçamentos'){
					progressOrcamentos = dataIndicadores[i].valor;

					// var progressOrcamentos = 72;

					if(progressOrcamentos == 100){
						collorOrcamentos = [
							"#0fba00",
							"#ffffff",
						]
					}

					if(progressOrcamentos > 75 && progressOrcamentos < 99){
						collorOrcamentos = [
							"#ffe900",
							"#ffffff",
						]
					}

					if(progressOrcamentos < 75 && progressOrcamentos > 50){
						collorOrcamentos = [
							"#ffc4c4",
							"#ffffff",
						]
					}

					if(progressOrcamentos < 50){
						collorOrcamentos = [
							"#ff0000",
							"#ffffff",
						]
					}
					var configOrcamentos = {
						type: 'doughnut',
						data: {
							labels: [
								"Atingido",
								"Não Atingido",
							],
							datasets: [{
								data: [progressOrcamentos, 100 - progressOrcamentos],
								backgroundColor: collorOrcamentos,
								hoverBackgroundColor: collorOrcamentos
							}]
						},
						options: {
							elements: {
								center: {
									text: progressOrcamentos + '%',
									color: '#050505', // Default is #000000
									fontStyle: 'Arial', // Default is Arial
									sidePadding: 20 // Defualt is 20 (as a percentage)
								}
							}
						}
					};
				}

				if(dataIndicadores[i].name == 'Ligações'){
					progressLigacoes = dataIndicadores[i].valor;

					// var progressLigacoes = 72;

					if(progressLigacoes == 100){
						collorLigacoes = [
							"#0fba00",
							"#ffffff",
						]
					}

					if(progressLigacoes > 75 && progressLigacoes < 99){
						collorLigacoes = [
							"#ffe900",
							"#ffffff",
						]
					}

					if(progressLigacoes < 75 && progressLigacoes > 50){
						collorLigacoes = [
							"#ffc4c4",
							"#ffffff",
						]
					}

					if(progressLigacoes < 50){
						collorLigacoes = [
							"#ff0000",
							"#ffffff",
						]
					}
					var configLigacoes = {
						type: 'doughnut',
						data: {
							labels: [
								"Atingido",
								"Não Atingido",
							],
							datasets: [{
								data: [progressLigacoes, 100 - progressLigacoes],
								backgroundColor: collorLigacoes,
								hoverBackgroundColor: collorLigacoes
							}]
						},
						options: {
							elements: {
								center: {
									text: progressLigacoes + '%',
									color: '#050505', // Default is #000000
									fontStyle: 'Arial', // Default is Arial
									sidePadding: 20 // Defualt is 20 (as a percentage)
								}
							}
						}
					};
				}

				if(dataIndicadores[i].name == 'Taxa de Conversão'){
					progressConversao = dataIndicadores[i].valor;

					// var progressConversao = 72;

					if(progressConversao == 100){
						collorConversao = [
							"#0fba00",
							"#ffffff",
						]
					}

					if(progressConversao > 75 && progressConversao < 99){
						collorConversao = [
							"#ffe900",
							"#ffffff",
						]
					}

					if(progressConversao < 75 && progressConversao > 50){
						collorConversao = [
							"#ffc4c4",
							"#ffffff",
						]
					}

					if(progressConversao < 50){
						collorConversao = [
							"#ff0000",
							"#ffffff",
						]
					}
					var configConversao = {
						type: 'doughnut',
						data: {
							labels: [
								"Atingido",
								"Não Atingido",
							],
							datasets: [{
								data: [progressConversao, 100 - progressConversao],
								backgroundColor: collorConversao,
								hoverBackgroundColor: collorConversao
							}]
						},
						options: {
							elements: {
								center: {
									text: progressConversao + '%',
									color: '#050505', // Default is #000000
									fontStyle: 'Arial', // Default is Arial
									sidePadding: 20 // Defualt is 20 (as a percentage)
								}
							}
						}
					};
				}
			}

			var ctxVendas = document.getElementById("myChart").getContext("2d");
			var myChart = new Chart(ctxVendas, configVendas);
			myChart.render()

			var ctxOrcamentos = document.getElementById("myChart2").getContext("2d");
			var myChart2 = new Chart(ctxOrcamentos, configOrcamentos);
			myChart2.render()

			var ctxLigacoes = document.getElementById("myChart3").getContext("2d");
			var myChart3 = new Chart(ctxLigacoes, configLigacoes);
			myChart3.render()

			var ctxConversao = document.getElementById("myChart4").getContext("2d");
			var myChart4 = new Chart(ctxConversao, configConversao);
			myChart4.render()

		});

	},1000);
}







