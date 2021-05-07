import React, { Component } from "react";
import { AgGridReact } from "ag-grid-react";
import {  withRouter } from 'react-router-dom'

import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-balham.css";

class Stocks extends Component {
    constructor(props) {
        super(props);       

        this.state = {
            columnDefs: [
                {
                    headerName: "Symbol", field: "symbol", sortable: true,
                    width: 120,
                    onCellClicked: (events) => {
                        this.props.history.push({
                            pathname: '/history',
                            symbol:  `${events.value}`,
                            industry:  events.data.name
                          })
                    }
                },
                { headerName: "Name", field: "name", width: 220, sortable: true},
                { headerName: "Industry", field: "industry", width: 350, sortable: true}
            ],
            rowData: [],
            selectedSymbol: "",
            displaystdcols: false,
            selectedcol: -1,
            industryList: []
        };

    }

    onGridReady = params => {
        this.gridApi = params.api;
        this.gridColumnApi = params.columnApi;

        const httpRequest = new XMLHttpRequest();
        const updateData = data => {
            this.setState({
                            rowData: data,
                            industryList : Array.from(new Set(data.map(s => s.industry)))
                            .map(industry => {
                                return {
                                    industry : industry,
                                }
                            })
                        });
        };

        httpRequest.open('GET','http://131.181.190.87:3001/all');
        httpRequest.send();
        httpRequest.onreadystatechange = () => {
            if (httpRequest.readyState === 4 && httpRequest.status === 200) {
                updateData(JSON.parse(httpRequest.responseText));
            }
        };

    };

    filterStocks = () => {
        var symbolFilterComponent = this.gridApi.getFilterInstance('symbol');
        symbolFilterComponent.setModel({
            type: 'contains',
            filter: document.querySelector('#symbolFilter').value === "0"
                ? ''
                : document.querySelector('#symbolFilter').value
        })
        this.gridApi.onFilterChanged();
    };

    filterIndustries = () => {

        var industryFilterComponent = this.gridApi.getFilterInstance('industry');
        industryFilterComponent.setModel({
            type: 'equals',
            filter: document.querySelector('#industryFilter').value === "0"
                ? ''
                : document.querySelector('#industryFilter').value
        })
        this.gridApi.onFilterChanged();
    };

    render() {

        return (

            <div
                className="ag-theme-balham"
                style={{border:"solid", height: "640px", width: "700px" }}
            >

                <br/>
                
                <div className = "Row-styles" style={{height: "5%", width: "100%" }} >

                    &nbsp;

                    <div style={{height: "50%", width: "40%" }}>

                        <select
                            id="symbolFilter"
                            onChange={this.filterStocks}
                        >

                            <option value="0">
                                {"Select a SYMBOL to be shown"}
                            </option>
                       
                             {this.state.rowData.map(stock => (
                                <option
                                    key={stock.symbol}
                                    value={stock.symbol}
                                >
                                    {stock.symbol}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div style={{height: "50%", width: "100%" }}>

                        <select
                            id="industryFilter"
                            onChange={this.filterIndustries}
                        >
                            <option value="0">
                                {"Select a INDUSTRY to be shown"}
                            </option>

                            {this.state.industryList.map(stock => (
                                
                                <option
                                    key={stock.industry}
                                    value={stock.industry}
                                >
                                    {stock.industry}
                                </option>

                            ))}
                        </select>

                        </div>
                </div>

                <div style={{height: "92%", width: "100%" }} >

                    <AgGridReact
                        columnDefs={this.state.columnDefs}
                        rowData={this.state.rowData}
                        onGridReady={this.onGridReady}
                        pagination={true}
                        paginationPageSize={20}
                    />
                </div>

            </div>
        );
    }
}

export default withRouter(Stocks);