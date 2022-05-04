import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { createAluno } from "../../slices/aluno";

const AddAluno = () => {
  const initialTutorialState = {
    id: null,
    nome: "",
    curso: "",
    published: false,
    teste: "",
    select: ""
  };
  const [tutorial, setTutorial] = useState(initialTutorialState);
  const [submitted, setSubmitted] = useState(false);

  const dispatch = useDispatch();

  const handleInputChange = event => {
    const { name, value } = event.target;
    setTutorial({ ...tutorial, [name]: value });
  };

  const options = [
    {
      label: "Apple",
      value: "apple",
    },
    {
      label: "Mango",
      value: "mango",
    },
    {
      label: "Banana",
      value: "banana",
    },
    {
      label: "Pineapple",
      value: "pineapple",
    },
  ];

  const saveTutorial = () => {
    const { nome, curso, teste, select } = tutorial;
    console.log(nome, curso, teste);
    dispatch(createAluno({ nome, curso, teste, select }))
      .unwrap()
      .then(data => {
        console.log(data);
        setTutorial({
          id: data.id,
          nome: data.nome,
          curso: data.curso,
          published: data.published,
          teste: data.teste,
          select: data.select
        });
        setSubmitted(true);
      })
      .catch(e => {
        console.log(e);
      });
  };

  const newTutorial = () => {
    setTutorial(initialTutorialState);
    setSubmitted(false);
  };

  return (
    <div className="submit-form">
      {submitted ? (
        <div>
          <h4>You submitted successfully!</h4>
          <button className="btn btn-success" onClick={newTutorial}>
            Add
          </button>
        </div>
      ) : (
        <div>
          <div className="form-group">
            <label htmlFor="nome">nome</label>
            <input
              type="text"
              className="form-control"
              id="nome"
              required
              value={tutorial.nome || ''}
              onChange={handleInputChange}
              name="nome"
            />
          </div>

          <div className="form-group">
            <label htmlFor="curso">curso</label>
            <input
              type="text"
              className="form-control"
              id="curso"
              required
              value={tutorial.curso || ''}
              onChange={handleInputChange}
              name="curso"
            />
          </div>

          <div className="form-group">
            <label htmlFor="curso">Teste</label>
            <input
              type="text"
              className="form-control"
              id="teste"
              required
              value={tutorial.teste || ''}
              onChange={handleInputChange}
              name="teste"
            />
          </div>

          <div className="form-group">
          <select 
            id="select"  
            name="select"
            onChange={handleInputChange}
            class="form-select form-select-lg mb-3"
          >
            <option selected>Open this select menu</option>
            {options.map((option) => (
              <option value={option.value}>{option.label}</option>
            ))}
          </select>
          </div>

          <button onClick={saveTutorial} className="btn btn-success">
            Submit
          </button>
        </div>
      )}
    </div>
  );
};

export default AddAluno;
