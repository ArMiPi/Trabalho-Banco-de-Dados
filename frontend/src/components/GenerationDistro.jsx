// GenerationDistro.jsx
import React, { useEffect, useState } from 'react';
import BarChart from './BarChart';
import './GenerationDistro.css';

export const GenerationDistro = () => {
    const [genData, setGenData] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/generation/pokemon_distribution`);
                const data = await response.json();
                setGenData(data);
                console.log(data);
            } catch (error) {
                console.error("Error fetching data", error);
            }
        };

        fetchData();
    }, []);

    return (
        <div>
            {genData && <BarChart data={genData} />}
        </div>
    );
};
