const labels = [
  'January',
  'February',
  'March',
  'April',
  'May',
  'June',
];

const data = {
  datasets: [{
    labels: labels,
    label: 'My First dataset',
    backgroundColor: 'rgb(255, 99, 132)',
    borderColor: 'rgb(255, 99, 132)',
    data: [{x: 1, y: 0}, 
        {x: 2, y: 10}, 
        {X: 3, y: 5}, 
        {x: 4, y: 2}, 
        {x: 5, y: 20}, 
        {x: 6, y: 30}, 
        {x: 7, y: 45}],
  }]
};

function customLabel(context) {
    return context.dataset.labels[context.dataIndex] || '';
}

const config = {
    type: 'scatter',
    data,
    options: {
        plugins: {
            tooltip: {
                callbacks: {
                    label: customLabel 
                }
            }
        }
    }
};

var myChart = new Chart(
    document.getElementById('myChart'),
    config
);

