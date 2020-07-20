import React, { Component } from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import { Navbar } from 'react-bootstrap';

import stocks from './Stocks';
import history from './History';
import logo from './logo.jpg';

export default class App extends Component {

    state = { activeItem: "home" };

    handleItemClick = (e, { name }) => this.setState({ activeItem: name });

    render() {
      const { activeItem } = this.state;

      return (
          <Router>
                   
              <div >

              <Navbar bg="dark" variant="dark">
                  <Link to={'/'} className="navbar-brand">Home</Link>

                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                      <ul className="navbar-nav mr-auto">
                        <li className="nav-item">
                          <Link to={'/Stocks'} className="nav-link">Stocks</Link>
                        </li>
                      </ul>
                    </div>
                </Navbar>

                <Switch>
                  <Route exact path='/Stocks' component={stocks} />
                  <Route path='/history' component={history} />
                </Switch>        

              </div>

          </Router>

      );
    }
}