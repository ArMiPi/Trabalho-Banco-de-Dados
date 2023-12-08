// BarChart.jsx
import React from 'react';
import { Bar } from 'react-chartjs-2';

const BarChart = ({ data }) => {
    const chartData = {
        labels: data.map(entry => entry.region),
        datasets: [
            {
                label: 'Count',
                data: data.map(entry => entry.count),
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1,
            },
        ],
    };

    const chartOptions = {
        scales: {
            x: {
                type: 'category',
            },
            y: {
                beginAtZero: true,
            },
        },
    };

    return <Bar data={chartData} options={chartOptions} />;
};

export default BarChart;
