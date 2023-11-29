import {useState} from "react";
// import { FaSearch } from "react-icons/fa";
import './SearchBar.css';


export const SearchBar = ({ setResults }) => {
    const [input, setInput] = useState("");

    const fetchData = (value) => {
      fetch("http://localhost:8080/pokemon/search")
          .then((response) => response.json())
          .then((json) =>{
              const results = json.filter((pokemon) => {
                  return (
                      value &&
                      pokemon &&
                      pokemon.name &&
                      pokemon.name.toLowerCase().includes(value)
                  );
              });
              setResults(results);
          });
    };
    const handleChange = (value) => {
        setInput(value);
        fetchData(value);
    };

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