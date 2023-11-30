import "./SearchResult.css";
import {NavLink, useNavigate} from "react-router-dom";

export const SearchResult = ({ result , id}) => {
    const navigate = useNavigate();

    const goToPokemon = () => {
        navigate(`/pokemon/${id}`);
    }
    return (
        // <div
        //     className="search-result"
        //     onClick={alert('nothing')} //(e) => alert(`You selected ${result}! \nthis will take you to /pokemon/${id}`)
        // >
        //     {result}
        // </div>
        <NavLink to={'/pokemon/' + id} className="search-result" style={{textDecoration: 'none', color:'black'}} onClick={goToPokemon}>{result}</NavLink>

    );
};