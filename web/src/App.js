import React from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AddTutorial from "./components/AddTutorial";
import Tutorial from "./components/Tutorial";
import TutorialsList from "./components/TutorialsList";

import AlunosList from "./components/Alunos/TutorialsList";
import AddAluno from "./components/Alunos/AddTutorial";
import Aluno from "./components/Alunos/Tutorial";

function App() {
  return (
    <Router>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <a href="/tutorials" className="navbar-brand">
          Inicio
        </a>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/tutorials"} className="nav-link">
              Agendamentos
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/add"} className="nav-link">
              Adicionar
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/alunos"} className="nav-link">
              Alunos
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/livros"} className="nav-link">
              Livros
            </Link>
          </li>   
        </div>              
      </nav>

      <div className="container mt-3">
        <Switch>
          <Route exact path={["/", "/tutorials"]} component={TutorialsList} />
          <Route exact path="/add" component={AddTutorial} />
          <Route path="/tutorials/:id" component={Tutorial} />

          <Route exact path="/alunos" component={AlunosList} />
          <Route exact path="/livros" component={TutorialsList} />

          <Route exact path="/adicionar/aluno" component={AddAluno} />
          <Route path="/alunos/:id" component={Aluno} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
