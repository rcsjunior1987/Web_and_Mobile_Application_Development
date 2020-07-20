import React, { Component } from "react";
import { AgGridReact } from "ag-grid-react";
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-balham.css";

import 'react-datepicker/dist/react-datepicker.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import './AgGrids.css';
import './css/Charts.css';
import './css/Divs.css';

import DatePicker from 'react-datepicker';
import moment from 'moment';

import ChartJs from './components/ChartJs';
import ChartD3 from './components/ChartD3';
import { black } from "material-ui/styles/colors";

export default class History extends Component {
    constructor(props) {
        super(props);

        this.state = {
            columnDefs: [
                { headerName: "Date", field: "timestamp", sortable: true,
                    width: 140,
                    cellRenderer: (data) => {
                        return data.value ? (new Date(data.value)).toLocaleDateString() : '';
                    }
                },
                { headerName: "Open", field: "open", cellStyle: { 'text-align': 'right' }, sortable: true,
                    width: 140,
                    cellRenderer: function (params) {
                        return Intl.NumberFormat().format(params.value);
                    }
                 },
                { headerName: "High", className: "NumericValues", cellStyle: { 'text-align': 'right' }, field: "high", sortable: true,
                    width: 140,
                    cellRenderer: function (params) {
                        return Intl.NumberFormat().format(params.value);
                    }
                 },
                { headerName: "Low", field: "low", cellStyle: { 'text-align': 'right' }, sortable: true,
                    width: 140,
                    cellRenderer: function (params) {
                        return Intl.NumberFormat().format(params.value);
                    }
                },
                { headerName: "Close", field: "close", cellStyle: { 'text-align': 'right' }, sortable: true,
                    width: 140,
                    cellRenderer: function (params) {
                        return Intl.NumberFormat().format(params.value);
                    }
                 },
                { headerName: "Volumes", field: "volumes", cellStyle: { 'text-align': 'right' }, sortable: true,
                    width: 140,
                    cellRenderer: function (params) {
                        return Intl.NumberFormat().format(params.value);
                    }
                 }
            ],
            rowData: [],
            rowDataBeforeFilter: [],
            selectedSymbol: "",
            date: moment().subtract(100, 'day').toDate(),
            chartValues:[],
            stockSymbol: "",
            industryName: ""

        };       
        this.dateChanged = this.dateChanged.bind(this);
        
    }
    
    onGridReady = params => {

        //alert(this.props.symbol)

        this.gridApi = params.api;
        this.gridColumnApi = params.columnApi;

        const httpRequest = new XMLHttpRequest();
        const updateData = data => {
            this.setState({ rowData: data });
        };

        httpRequest.open('GET', 'http://131.181.190.87:3001/history?symbol='+ this.state.stockSymbol + '&from=' +  this.state.date);
        httpRequest.send();
        httpRequest.onreadystatechange = () => {
            if (httpRequest.readyState === 4 && httpRequest.status === 200) {
                updateData(JSON.parse(httpRequest.responseText));
            }
        };
    };
    

    filterDate = () => {

        if (this.state.rowDataBeforeFilter.length === 0)
            this.state.rowDataBeforeFilter = this.state.rowData
        else
            this.state.rowData = this.state.rowDataBeforeFilter

        this.setState(
            {
                rowData: this.state.rowData.filter(item => (new Date(item.timestamp)) >= (new Date(this.state.date)) )
            }
        );

    }

    dateChanged(d){
        this.setState({date: d});
    }  

    render() {

        const { symbol } = this.props.location
        const { industry } = this.props.location

        this.state.stockSymbol = symbol
        this.state.industryName = industry

        return (

            <div className="ag-theme-balham" style={{border:"solid", height: "640px", width: "1400px" }} >

                <br/>

                <div className = "Row-styles" style={{height: "6%", width: "100%" }} >
                    
                    <div style={{ height: "100%", width: "18%" }}
                    >
                        &nbsp;

                        <label>Search date from: </label>

                        &nbsp;        

                        <DatePicker id="searchDate" selected={this.state.date}
                            onChange={this.dateChanged}
                        />                      

                    </div>

                    <div style={{ height: "100%", width: "77%" }} >

                        <button id="searchDate" onClick={this.filterDate} style={{ height: 30, width: 90 }}>
                            Search
                        </button>                                                    

                    </div>

                </div>

                <div style={{boxSizing: "border-box", height: "30%", width: "100%" }} className="ag-theme-balham" >

                    &nbsp;&nbsp;&nbsp;


                    <label> <strong> [ Showing stocks for the {this.state.industryName} ] </strong> </label>

                    <AgGridReact
                        columnDefs={this.state.columnDefs}
                        rowData={this.state.rowData}
                        onGridReady={this.onGridReady}
                        pagination={true}
                        paginationPageSize={10}
                    />

                </div>

                <div className = "Row-styles"
                    style={{height: "61%", width: "100%" }}>

                    <div id="c1" className = "Column1-styles">
                        <ChartJs label="Closing Price" data= {this.state.rowData} />
                    </div>

                    <div id="c2" className = "Column2-styles"> 
                        <ChartD3 dataSet1= {this.state.rowData} />
                    </div>

                </div>
                

            </div>

        );
    }  

}