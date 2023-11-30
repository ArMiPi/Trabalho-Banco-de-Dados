import React, {useState} from 'react';
import './App.css';
import {SearchBar} from "./components/SearchBar";
import {ListResults} from "./components/ListResults";

function App() {
    const [results, setResults] = useState([]);

  return (
    <div className="App">
      <div className="search-bar-container">
          <SearchBar setResults={setResults} />
          {results && results.length > 0 && <ListResults results={results} /> }
      </div>
    </div>
  );
}

export default App;
