import React, { useState, useContext, useEffect } from "react";
import { AsyncStorage } from "react-native";

const StocksContext = React.createContext();

export const StocksProvider = ({ children }) => {
  const [state, setState] = useState({ 
                                      watchList: []
                                    , newWatchlist: []
                                    , newSymbol: []
                                    });
  return (
    <StocksContext.Provider value={[state, setState]}>
      {children}
    </StocksContext.Provider>
  );
};

export const useStocksContext = () => {
  const [state, setState] = useContext(StocksContext);

  function updateWatchList(newWatchList) {
    setState(oldState =>  ({...oldState, watchList: newWatchList}) );
  }

  function  addToWatchlist(newSymbol) {
    state.newSymbol = newSymbol

    if(state.watchList !== null)
      state.newWatchlist = state.watchList

    state.newWatchlist.push(newSymbol)
    AsyncStorage.setItem('WatchList', JSON.stringify(state.newWatchlist));
    updateWatchList(state.newWatchlist);
  }

  const FetchValue = () => {
    AsyncStorage.getItem("WatchList").then((value) => {
      updateWatchList(JSON.parse(value))
    });
  };

  useEffect(() => {
    FetchValue();
  }, []);
  
  return { ServerURL: 'http://131.181.190.87:3001', watchList: state,  addToWatchlist, newSymbol: state.newSymbol };
};
