import {useState} from "react";
// import { FaSearch } from "react-icons/fa";
import './SearchBar.css';


export const SearchBar = ({ setResults }) => {
    const [input, setInput] = useState("");
    let pokeNames = [];
    fetch("http://localhost:8080/pokemon/search")
        .then((response) => response.json())
        .then((json) => {
            json.forEach((pokemon) => {
                pokeNames.push({name: pokemon.name.toLowerCase(), id: pokemon.idPokedex});
            });
        });

    const handleChange = (value) => {
        setInput(value);
        console.log(pokeNames.filter((pokemon) =>
            pokemon.name.includes(value.toLowerCase())
        ));
        const filtered = pokeNames.filter((pokemon) =>
            pokemon.name.includes(value.toLowerCase())
        );
        setResults(filtered);
    }
    return(
        <div className="input-wrapper">

            <input
                placeholder="Type your pokemon here..."
                value = {input}
                onChange={(e) => handleChange(e.target.value)}
            />
        </div>
    );
};