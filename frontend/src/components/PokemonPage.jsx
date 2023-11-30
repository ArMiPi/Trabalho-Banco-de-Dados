import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";


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

    return(
        <div className="table-container">
            <h1>{pokemonData.name}#{padded}</h1>
            <table >
                <tr>
                    <td>
                        <img src={pokemonData.sprite} alt={pokemonData.name}/>
                    </td>
                    <td>
                        <table>
                            <tr>
                                <td>Height:</td>
                                <td>{pokemonData.height}</td>
                            </tr>
                            <tr>
                                <td>Weight:</td>
                                <td>{pokemonData.weight}</td>
                            </tr>
                            <tr>
                                <td>Capture Rate:</td>
                                <td>{pokemonData.captureRate}</td>
                            </tr>
                            <tr>
                                <td>Evolves From:</td>
                                <td>{pokemonData.evolvesFrom}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>

        </div>
    )

}