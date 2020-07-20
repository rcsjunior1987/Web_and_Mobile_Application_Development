import React, { useState, useEffect } from 'react';

import { StyleSheet
       , View
       , TouchableWithoutFeedback
       , Text
       , Image
       , FlatList} from 'react-native';

import { useStocksContext } from '../contexts/StocksContext';
import { scaleSize } from '../constants/Layout';
import { SearchBar } from 'react-native-elements';

export default function SearchScreen({ navigation }) {
  const { ServerURL, watchList, addToWatchlist } = useStocksContext();
  const [state, setState] = useState({
    searchText: "",
    data: [],
    stockList: [],
    newSymbol: { symbol: ''}
  });

  function updateWatchList(stockList) {

    var listAllSymbol = stockList

    watchList.watchList.forEach(function(item) {
      const index  = listAllSymbol.findIndex(i => i.symbol === item.symbol); 
        listAllSymbol.splice(index, 1);
      } 
      
    );

    state.stockList = listAllSymbol
  }

  function searchFilter(text) {
    const newData = state.stockList.filter(item => {
      const itemData = `${item.symbol.toUpperCase()}`;
       const textSymbol = text.toUpperCase();

       return itemData.indexOf(textSymbol) > -1;    
    });
    
    setState({       
      data: newData,
      stockList: state.stockList,
      searchText: text
    });  
  };
  
  function onPress(newSymbol) {    
    const position = state.stockList.findIndex(i => i.symbol === newSymbol.symbol);
    state.stockList.splice(position, 1);

    searchFilter(state.searchText)

    addToWatchlist(newSymbol);
  }

  function updateStockList(newStockList) {
    setState(oldState =>  ({...oldState, stockList: newStockList}) );
  }

  useEffect(() => {
    async function DoFetch() {

      try { 
        const response = await fetch(ServerURL + '/all');
        if (response.ok) {

          state.stockList = await response.json();
          updateWatchList(state.stockList);
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

          <SearchBar style={styles.search}
            round
            onChangeText={text => searchFilter(text)}
            onClear={text => searchFilter('')}
            placeholder="Type Here..."
            value={state.searchText}
          />
      
      
            <FlatList
              data={state.data}              
              keyExtractor={item => item.symbol}
              ItemSeparatorComponent={()  =>
                <View style={styles.separator} />
              }

              renderItem={({ item }) =>
              <TouchableWithoutFeedback onPress={() => ( onPress( { symbol: item.symbol }), navigation.navigate('Stocks') )} >
			          <View style={styles.item}> 
				          <Text style={styles.title} >{ item.symbol }</Text>
				          <Text style={styles.subTitle}>{ item.name}</Text>
                </View>
                </TouchableWithoutFeedback>

	            }
              
            />

      </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#2e2e2e',
    margin: scaleSize(5),
    width: '100%',
    fontSize: scaleSize(20),
    padding: scaleSize(10)
  },
  search: {
    backgroundColor: 'white',
    width: '100%'
  },
  item: {
    height: scaleSize(25),
    backgroundColor: '#2e2e2e',
    marginVertical: 0.2

  },
  title: {
    color: 'white',
    fontSize: scaleSize(8),
  },
  subTitle: {
    color: 'white',
    fontSize: scaleSize(7),
  },
  separator: {
    height: 0.4,
    width: '100%',
    backgroundColor: 'white'
  }

});