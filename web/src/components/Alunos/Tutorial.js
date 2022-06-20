import React, { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { updateAluno, deleteAluno } from "../../slices/aluno";
import AlunoDataService from "../../services/AlunoService";
import Swal from 'sweetalert2'

const Aluno = (props) => {
  const initialTutorialState = {
    id: null,
    nome: "",
    curso: "",
    published: false
  };
  const [currentTutorial, setCurrentTutorial] = useState(initialTutorialState);
  const [message, setMessage] = useState("");

  const dispatch = useDispatch();

  const getTutorial = id => {
    AlunoDataService.get(id)
      .then(response => {
        setCurrentTutorial(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  useEffect(() => {
    getTutorial(props.match.params.id);
  }, [props.match.params.id]);

  const handleInputChange = event => {
    const { name, value } = event.target;
    setCurrentTutorial({ ...currentTutorial, [name]: value });
  };

  const updateStatus = status => {
    const data = {
      id: currentTutorial.id,
      nome: currentTutorial.nome,
      curso: currentTutorial.curso,
      published: status
    };

    dispatch(updateAluno({ id: currentTutorial.id, data }))
      .unwrap()
      .then(response => {
        console.log(response);
        setCurrentTutorial({ ...currentTutorial, published: status });
        setMessage("The status was updated successfully!");
      })
      .catch(e => {
        console.log(e);
      });
  };

  const updateContent = () => {
     
    Swal.fire({
      title: 'Vocë tem certeza que quer atualizar?',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: 'Sim',
      denyButtonText: `Nao`,
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        Swal.fire('Atualizado com sucesso!', '', 'success')
        dispatch(updateAluno({ id: currentTutorial.id, data: currentTutorial }))
      .unwrap()
      .then(response => {
        console.log(response);
        setMessage("Aluno atualizado");
      })
      .catch(e => {
        console.log(e);
      });
      } else if (result.isDenied) {
        Swal.fire('Alteraçoes nao salva', '', 'info')
      }
    })
    
  };

  const removeTutorial = () => {
    Swal.fire({
      title: 'Vocë tem certeza que quer apagar o aluno?',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: 'Sim',
      denyButtonText: `Don't save`,
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        Swal.fire('Aluno deletado com sucesso!', '', 'success')
        dispatch(deleteAluno({ id: currentTutorial.id }))
        .unwrap()
        .then(() => {
          props.history.push("/alunos");
        })
        .catch(e => {
          console.log(e);
        });
      } else if (result.isDenied) {
        Swal.fire('Aluno nao deletado', '', 'info')
       
      }
    })
  
  };

  return (
    <div>
      {currentTutorial ? (
        <div className="edit-form">
          <h4>Tutorial</h4>
          <form>
            <div className="form-group">
              <label htmlFor="nome">nome</label>
              <input
                type="text"
                className="form-control"
                id="nome"
                name="nome"
                value={currentTutorial.nome}
                onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="curso">curso</label>
              <input
                type="text"
                className="form-control"
                id="curso"
                name="curso"
                value={currentTutorial.curso}
                onChange={handleInputChange}
              />
            </div>

            {/* <div className="form-group">
              <label>
                <strong>Status:</strong>
              </label>
              {currentTutorial.published ? "Published" : "Pending"}
            </div> */}
          </form>

          {/* {currentTutorial.published ? (
            <button
              className="badge badge-primary mr-2"
              onClick={() => updateStatus(false)}
            >
              UnPublish
            </button>
          ) : (
            <button
              className="badge badge-primary mr-2"
              onClick={() => updateStatus(true)}
            >
              Publish
            </button>
          )} */}

          <button className="m-3 btn btn-sm btn-danger"  onClick={removeTutorial}>
            Deletar
          </button>

          <button
            type="submit"
            className="m-3 btn btn-sm btn-success" 
            onClick={updateContent}
          >
            Atualizar
          </button>
          <p>{message}</p>
        </div>
      ) : (
        <div>
          <br />
          <p>Selecione um...</p>
        </div>
      )}
    </div>
  );
};

export default Aluno;
