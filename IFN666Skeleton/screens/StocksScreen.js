import React, { useState, useEffect, useContext } from 'react';

import { StyleSheet
       , View
       , FlatList } from 'react-native';

import { DataTable } from 'react-native-paper';

import { useStocksContext } from '../contexts/StocksContext';
import { scaleSize } from '../constants/Layout';

export default function StocksScreen({route}) {
  const { ServerURL, watchList, newSymbol } = useStocksContext();
  const [state, setState] = useState( { watchList: ['']
                                      , tableHead: ['Open', 'Close', 'Low', 'High'],
                                    } );

  function updateWatchList(newWatchList) {
    setState(oldState =>  ({...oldState, watchList: newWatchList}) );
  }

  useEffect(() => {

    if(state.watchList.length === 0 || newSymbol.symbol === undefined) {

      if (watchList.watchList.length > 0) {

        watchList.watchList.forEach(item => 
          DoFetch(item.symbol)
        );

      }

    } else
        DoFetch(newSymbol.symbol)

    async function DoFetch(symbol) {
    
      try { 
        const serverURL = ('http://131.181.190.87:3000' +'/stocks/' + symbol)

        alert(serverURL)

        const response = await fetch(serverURL);
        if (response.ok) {
          const newWatchList = state.watchList
          const newSymbol = await response.json()

          const found = newWatchList.some(newWatchList => newWatchList.symbol === newSymbol.symbol);

          if (found === false) {            
            newWatchList.push(newSymbol);
            updateWatchList(newWatchList);
          }

        }
        else
          throw new Error('Fetch failed, status: ' + response.status);
      }
      catch (message) {
        console.warn(message);
      }

    }

    DoFetch();
  }, [watchList]);
 
  return (
    <View style={styles.container}>

        <FlatList
          data={state.watchList}              
          keyExtractor={item => item.symbol}
          ItemSeparatorComponent={()  =>
            <View style={styles.separator} />
              }
                renderItem={({ item }) =>

			            <View style={styles.item}> 

                  <DataTable >
                    <DataTable.Header style={styles.title} >
                      <DataTable.Title > { item.symbol } - { item.name } </DataTable.Title>                    
                    </DataTable.Header>

                    <DataTable.Header >
                      <DataTable.Title> Open </DataTable.Title>
                      <DataTable.Title> Close  </DataTable.Title>
                      <DataTable.Title> Low </DataTable.Title>
                      <DataTable.Title> High </DataTable.Title>
                    </DataTable.Header>

                    <DataTable.Row>
                      <DataTable.Cell numeric> { item.open } </DataTable.Cell>
                      <DataTable.Cell numeric> { item.close } </DataTable.Cell>
                      <DataTable.Cell numeric> { item.low } </DataTable.Cell>
                      <DataTable.Cell numeric> { item.high } </DataTable.Cell>
                    </DataTable.Row>

                  </DataTable>
                    

                  </View>
	            }
            />

    </View>

  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#2e2e2e',
    margin: scaleSize(5),
    width: '100%',
    height: scaleSize(300),
    fontSize: scaleSize(20),
    padding: scaleSize(10)
  },
  table: {
    width: '100%'
  },
  title: {
    width: '100%',    
    color: 'black',
    fontSize: scaleSize(8),
  },
  subTitle: {
    width: '100%',
    color: 'white',
    fontSize: scaleSize(7),
  },
  item: {
    width: '100%',
    height: scaleSize(130),
    backgroundColor: 'white',
    marginVertical: 0.2
  },
  separator: {
    width: '100%',
    height: 1,
    backgroundColor: 'black'
  }
  });