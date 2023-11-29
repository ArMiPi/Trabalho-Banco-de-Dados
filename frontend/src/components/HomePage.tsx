import react from 'react';
import './HomePage.css';

const HomePage: react.FC = () => {
    return (
        <div className="HomePage">
            <div className="search-container">
                <input type="text" placeholder="Type your Pokemon.." name="search" />
                <button type="submit">Search</button>

            </div>
        </div>
    )
}

export default HomePage;