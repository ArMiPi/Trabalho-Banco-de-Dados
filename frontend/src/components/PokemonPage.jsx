import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import './PokemonPage.css';


export const PokemonPage = () => {
    const {id} = useParams();
    const [pokemonData, setPokemonData] = useState(null);
    //pad iD with zeroes to make it 4 digits
    const padded = (id + "").padStart(4, "0");

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/pokemon/${id}`);
                const data = await response.json();
                setPokemonData(data);
            } catch (error) {
                console.error("Error fetching data", error);
            }
        };
        fetchData();
    }, [id]);
    if(!pokemonData){
        return <div>Loading...</div>;
    }
    const abilities = pokemonData.abilities;
    const GoInfo = pokemonData.pokemon_go_info;

    const typeColors = {
        normal: '#A8A77A',
        fire: '#F08030',
        water: '#6890F0',
        electric: '#F8D030',
        grass: '#78C850',
        ice: '#98D8D8',
        fighting: '#C03028',
        poison: '#A040A0',
        ground: '#E0C068',
        flying: '#A890F0',
        psychic: '#F85888',
        bug: '#A8B820',
        rock: '#B8A038',
        ghost: '#705898',
        dragon: '#7038F8',
        dark: '#705848',
        steel: '#B8B8D0',
        fairy: '#EE99AC',
    }

    const convertRarity = (rarity) => {
        switch (rarity) {
            case 'N':
                return "Normal";
            case 'L':
                return "Legendary";
            case 'M':
                return "Mythical";
            default:
                return "Unknown";
        }
    }

    return(
        <div className="page-container">
            <h1>{pokemonData.name}#{padded}</h1>
            <div className="pokeImg">
                <img src={pokemonData.sprite} alt={pokemonData.name}/>

                <img src={GoInfo.shiny.sprite} alt={pokemonData.name}/>

            </div>

            <div className="table-container">
                <div className="tb-cell">
                    <span className="tb-cell-title">Weight:</span>
                    <span className="tb-cell-data">{pokemonData.weight}</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">Height:</span>
                    <span className="tb-cell-data">{pokemonData.height}</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">Capture Rate:</span>
                    <span className="tb-cell-data">{pokemonData.capture_rate}</span>
                </div>
                {pokemonData.evolves_from !== null && (
                    <div className="tb-cell">
                        <span className="tb-cell-title">Evolves From:</span>
                        <span className="tb-cell-data">{pokemonData.evolves_from}</span>
                    </div>
                )}
                <div className="tb-cell">
                    <span className="tb-cell-title">Rarity:</span>
                    <span className="tb-cell-data">{convertRarity(pokemonData.rarity)}</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">HP:</span>
                    <span className="tb-cell-data">{pokemonData.status['base_hp']}</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">Base Defense:</span>
                    <span className="tb-cell-data">{pokemonData.status['base_defense']}</span>
                </div>

                <div className="tb-cell">
                    <span className="tb-cell-title">Base Attack:</span>
                    <span className="tb-cell-data">{pokemonData.status['base_attack']}</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">SP Defense:</span>
                    <span className="tb-cell-data">{pokemonData.status['base_sp_defense']}</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">SP Attack:</span>
                    <span className="tb-cell-data">{pokemonData.status['base_sp_attack']}</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">Speed:</span>
                    <span className="tb-cell-data">{pokemonData.status['base_speed']}</span>
                </div>

                <div className="tb-cell-scroll">
                    <span className="tb-cell-title">Abilities:</span>
                    <ul>
                        {abilities.map((ability, index) => (
                            <li key={index}>
                                {ability.name}
                                {ability.hidden && <strong style={{fontSize: '9px'}}> (Hidden)</strong>}
                            </li>
                        ))}
                    </ul>

                </div>
                <div className="tb-cell-scroll">
                    <span className="tb-cell-title">GoStats:</span>
                    <ul>
                        <li>Max CP: {GoInfo.max_cp}</li>
                        <li>Defense: {GoInfo.stats.defense}</li>
                        <li>Attack: {GoInfo.stats.attack}</li>
                        <li>Stamina: {GoInfo.stats.stamina}</li>
                        <li>Buddy Distance: {GoInfo.buddy_distance}</li>
                        {GoInfo.raid_exclusive && <li>Raid Exclusive</li>}
                        <span className="tb-cell-title">Shiny from:</span>
                        {GoInfo.shiny.egg && <li>Egg</li>}
                        {GoInfo.shiny.raid && <li>Raid</li>}
                        {GoInfo.shiny.wild && <li>Wild</li>}
                    </ul>
                </div>
                <div className="tb-cell-scroll">
                    <span className="tb-cell-title">Types:</span>
                    <ul>
                        {pokemonData.types.map((type, index) => (
                            <li key={index} style={{backgroundColor: typeColors[type.name.toLowerCase()], width: '80px', borderRadius: '5px', textAlign: 'center'}} >
                                {type.name}
                            </li>
                        ))}
                    </ul>
                </div>


            </div>
        </div>

    )

}