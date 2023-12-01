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
        // <div className="table-container">
        //     <h1>{pokemonData.name}#{padded}</h1>
        //     <table >
        //         <tr>
        //             <td>
        //                 <img src={pokemonData.sprite} alt={pokemonData.name}/>
        //             </td>
        //             <td>
        //                 <table>
        //                     <tr>
        //                         <td>Height:</td>
        //                         <td>{pokemonData.height}</td>
        //                     </tr>
        //                     <tr>
        //                         <td>Weight:</td>
        //                         <td>{pokemonData.weight}</td>
        //                     </tr>
        //                     <tr>
        //                         <td>Capture Rate:</td>
        //                         <td>{pokemonData.captureRate}</td>
        //                     </tr>
        //                     <tr>
        //                         <td>Evolves From:</td>
        //                         <td>{pokemonData.evolvesFrom}</td>
        //                     </tr>
        //                 </table>
        //             </td>
        //         </tr>
        //     </table>
        //     <h3>outra coisa</h3>
        //     <div className="flex-container">
        //         <div className="flex-item">flex item</div>
        //         <div className="flex-item">flex item</div>
        //         <div className="flex-item">flex item</div>
        //         <div className="flex-item">flex item</div>
        //
        //     </div>
        // </div>

        <div className="page-container">
            <h1>{pokemonData.name}#{padded}</h1>
            <div className="pokeImg">
                <img src={pokemonData.sprite} alt={pokemonData.name}/>
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
                    <span className="tb-cell-data">{pokemonData.captureRate}</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">Evolves From:</span>
                    <span className="tb-cell-data">{pokemonData.evolvesFrom !== null ? pokemonData.evolvesFrom : "None"}</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">Rarity:</span>
                    <span className="tb-cell-data">{convertRarity(pokemonData.rarity)}</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">thing:</span>
                    <span className="tb-cell-data">thing</span>
                </div>
                {/*test*/}

                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>
                <div className="tb-cell">
                    <span className="tb-cell-title">dummy:</span>
                    <span className="tb-cell-data">dummy</span>
                </div>



            </div>
        </div>

    )

}