import {  useEffect, useState } from 'react';
import './App.css';
import Formulario from './Formulario';
import Tabela from './Tabela';

function App() {

  const carros = {
    codigo: 0,
    marca:"",
    modelo:"",
    ano:""
  }

  const [btnCadastrar,setBtnCadastrar] = useState(true)
  const [carro,setCarro] = useState([])
  const[objCarros,setObJcarros] = useState(carros);
  useEffect(()=>{
    fetch("http://localhost:8080/listar")
    .then(retorno => retorno.json())
    .then(retorno_convertido => setCarro(retorno_convertido));
  },[]);

  const aoDigitar = (e) =>{
    setObJcarros({...objCarros,[e.target.name]:e.target.value});
  }

  const cadastrar = () =>{
    fetch("http://localhost:8080/cadastrar",{
      method:"post",
      body:JSON.stringify(objCarros),
      headers:{
        "Content-type":"application/json",
        "Accept":"application/json"
      }
    })
    .then(retorno =>retorno.json())
    .then(retorno_convertido=>{
      if (retorno_convertido.mensagem !==undefined) {
        alert(retorno_convertido.mensagem);
      }else{
        setCarro([...carro,retorno_convertido]);
        alert("produto cadastrado com sucesso")
        limparFormulario();
      }
    })
  }

  const limparFormulario = ()=>{
    setObJcarros(carros);
  }
  const selecionarproduto =(indice) =>{
    setObJcarros(carro[indice]);
    setBtnCadastrar(false);
  }

  const remover = () =>{
    fetch("http://localhost:8080/remover/"+objCarros.codigo,{
      method:"delete",

      headers:{
        "Content-type":"application/json",
        "Accept": "application/json"
      }
    })
    .then(retorno =>retorno.json())
    .then(retorno_convertido=>{
      alert(retorno_convertido.mensagem);
      let vetortemp =[...carro];

      let indice = vetortemp.findIndex((p)=>{
        return p.codigo===objCarros.codigo;
      });
      vetortemp.splice(indice,1);

      setCarro(vetortemp);

      limparFormulario();
    })
  }

  const alterar = () => {
    fetch("http://localhost:8080/alterar",{
      method:"put",
      body:JSON.stringify(objCarros),
      headers:{
        "Content-type":"application/json",
        "Accept": "application/json"
      }
    })
    .then(retorno =>retorno.json())
    .then(retorno_convertido=>{
      if (retorno_convertido.mensagem !== undefined) {
        alert(retorno_convertido.mensagem);
      }else{
        alert("produto alterado com sucesso");

        let vetortemp = [...carro];
        let indice = vetortemp.findIndex((p)=>{
          return p.codigo===objCarros.codigo;
        });
        vetortemp[indice] = objCarros;
        setCarro(vetortemp);

        limparFormulario();
      }
    })
  }
  return (
    <div >
      <p>{JSON.stringify(objCarros)}</p>
      <Formulario botao = {btnCadastrar} eventoTeclado={aoDigitar} cadastrar={cadastrar} obj={objCarros} cancelar = {limparFormulario} remover = {remover} alterar = {alterar} />
      <Tabela vetor ={carro} selecionar = {selecionarproduto}/>
    
    </div>
  );
}

export default App;
