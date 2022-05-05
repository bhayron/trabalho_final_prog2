import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { createAluno } from "../../slices/aluno";
import Swal from 'sweetalert2'
import { Link } from "react-router-dom";

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


  const saveTutorial = () => {
    const { nome, curso, teste, select } = tutorial;
    console.log(nome, curso, teste);
 
Swal.fire({
  title: 'Voce deseja realmente adicionar um novo aluno?',
  showDenyButton: true,
  showCancelButton: true,
  confirmButtonText: 'Sim',
  denyButtonText: `Nao`,
}).then((result) => {
  /* Read more about isConfirmed, isDenied below */
  if (result.isConfirmed) {
    Swal.fire('Aluno salvo com sucesso!', '', 'success')
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
  } else if (result.isDenied) {
    Swal.fire('Aluno nao salvo', '', 'info')
  }
})
  
  };

  const newTutorial = () => {
    setTutorial(initialTutorialState);
    setSubmitted(false);
  };

  return (
    <div className="submit-form">
      {submitted ? (
        <div>
          <h4>Aluno adicionado com sucesso</h4>
          <button className="btn btn-warning" onClick={newTutorial}>
            Adicionar novo
          </button>
      {' '}
          <Link
              to={"/alunos"}
              
            >
          <button className="btn btn-wraning" onClick={newTutorial}>
            Voltar a listagem
          </button>
          </Link>
        </div>
      ) : (
        <div>
          <div className="form-group">
            <label htmlFor="nome">Nome</label>
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
            <label htmlFor="curso">Curso</label>
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

          <button onClick={saveTutorial} className="btn btn-success">
            Salvar
          </button>
        </div>
      )}
    </div>
  );
};

export default AddAluno;
