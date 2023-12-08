// Ranking.jsx
import React, { useEffect, useState } from 'react';
import './PokemonPage.css';

const Ranking = () => {
    const [pokemonRanking, setPokemonRanking] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/pokemon/ranking/`);
                const data = await response.json();
                setPokemonRanking(data);
                console.log(data);
            } catch (error) {
                console.error("Error fetching data", error);
            }
        };

        fetchData();
    }, []);

    return (
        <div className="page-container">

            <h2>Pokémon Ranking</h2>
            <ul>
                {pokemonRanking &&
                    pokemonRanking.map((pokemon) => (
                        <li key={pokemon.id_pokemon}>
                            {pokemon.name}
                        </li>
                    ))}
            </ul>
        </div>
    );
};

export default Ranking;
