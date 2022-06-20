import React, { useState, useEffect } from "react";
import { View, StyleSheet } from 'react-native';
import { DataTable } from 'react-native-paper';
import { api } from './api';

export default function App() {
  const [data, setData] = useState([]);

  const Alunos = async () => {
    const response = await api.get('/api/alunos')
    
    setData(response.data)
  }

  useEffect(()=>{
    Alunos()
  },[])
  
  return (
    <View style={styles.container}>
        <DataTable>
        <DataTable.Header>
          <DataTable.Title>Nome</DataTable.Title>
          <DataTable.Title>Curso</DataTable.Title>
          <DataTable.Title numeric>ID</DataTable.Title>
        </DataTable.Header>
          { data.map( (func) => 
          
          <DataTable.Row key={func.id}>
          <DataTable.Cell>{func.nome}</DataTable.Cell>
          <DataTable.Cell>{func.curso}</DataTable.Cell>
          <DataTable.Cell numeric>{func.id}</DataTable.Cell>
        </DataTable.Row>)  }
       

      </DataTable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingTop: 100,
    paddingHorizontal: 30,
  },
});