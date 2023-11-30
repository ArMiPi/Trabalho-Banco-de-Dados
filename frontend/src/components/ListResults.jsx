import "./ListResults.css";
import { SearchResult} from "./SearchResult";

export const ListResults = ({ results }) => {

    return (
        <div className="results-list">
            {results.map((result) => (
                <SearchResult result={result.name} id={result.id}/>
            ))}
        </div>
    );
};