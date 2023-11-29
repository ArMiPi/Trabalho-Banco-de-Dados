import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';
import HomePage from "./components/HomePage";
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
