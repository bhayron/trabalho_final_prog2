import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { createTutorial } from "../slices/tutorials";

const AddTutorial = () => {
  const initialTutorialState = {
    id: null,
    title: "",
    description: "",
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
    const { title, description, teste, select } = tutorial;
    console.log(title, description, teste);
    dispatch(createTutorial({ title, description, teste, select }))
      .unwrap()
      .then(data => {
        console.log(data);
        setTutorial({
          id: data.id,
          title: data.title,
          description: data.description,
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
            <label htmlFor="title">Title</label>
            <input
              type="text"
              className="form-control"
              id="title"
              required
              value={tutorial.title || ''}
              onChange={handleInputChange}
              name="title"
            />
          </div>

          <div className="form-group">
            <label htmlFor="description">Description</label>
            <input
              type="text"
              className="form-control"
              id="description"
              required
              value={tutorial.description || ''}
              onChange={handleInputChange}
              name="description"
            />
          </div>

          <div className="form-group">
            <label htmlFor="description">Teste</label>
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

export default AddTutorial;
