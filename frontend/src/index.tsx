import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {createBrowserRouter, RouterProvider, useLocation} from "react-router-dom";
import {PokemonPage} from "./components/PokemonPage.jsx";
import {GenerationDistro} from "./components/GenerationDistro";
import Ranking from "./components/Ranking";
import RankingNormal from "./components/RankingNormal";

const router = createBrowserRouter([
    {
        path: '/',
        element: <App/>
    },
    {
        path: '/pokemon/:id',
        element: <PokemonPage/>
    },
    {
        path: '/gen_distro/',
        element: <GenerationDistro/>
    },
    {
        path: '/ranking/',
        element: <Ranking/>
    },
    {
        path: '/ranking/normal',
        element: <RankingNormal/>
    }


]);

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

root.render(
  // <React.StrictMode>
  //   <App />
  // </React.StrictMode>
    <RouterProvider router={router} />
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
