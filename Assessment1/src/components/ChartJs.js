import React, { Component } from "react";
import ReactDOM from "react-dom";
import { Line } from 'react-chartjs-2';

class ChartJs extends Component {
    constructor(props) {
        super(props);

            this.state = {
                chartData: []
            }

    }

    static defaultPropos = {
        label:"",
        data:[]
    }

    PrepareChartMatrix = () => {
        var chartLabels = new Array([]);

        var chartOpenValues = new Array([]);
        var chartCloseValues = new Array([]);

        if (this.props.data.length === 0) {
            alert("Invalid date!")

            this.setState({
                chartData: []
            });

        } else {
                  
            var values = this.props.data;

            values.sort(function(a, b){
                var x = a.timestamp;
                var y = b.timestamp;

                if ((new Date(x)) < (new Date(y))) {return -1;}
                if ((new Date(x)) > (new Date(y))) {return 1;}
                return 0;
              });


            var dt = new Date(values[0].timestamp).toLocaleDateString();

            var vlOpen = 0;
            var vlClose = 0;

            var chartLabel = new Array([]);
            var chartValue = new Array([]);

            var i =0;      

            values.forEach(myFunction)
            function myFunction(item, index, arr) {

                if(new Date(dt).toLocaleDateString() === new Date(arr[index].timestamp).toLocaleDateString()) {
                    vlOpen = vlOpen + arr[index].open;
                    vlClose = vlClose + arr[index].close;
                } else {
                    chartValue = {data: vlOpen}
                    chartOpenValues[i] = chartValue;

                    chartValue = {data: vlClose}
                    chartCloseValues[i] = chartValue;

                    chartLabel = {data: new Date(dt).toLocaleDateString()}
                    chartLabels[i] = chartLabel;

                    i = i + 1;

                    dt = new Date(arr[index].timestamp).toLocaleDateString();

                    vlOpen = arr[index].open;
                    vlClose = arr[index].close;

                    if(arr[index+1] === undefined) {
                        chartValue = {data: vlOpen}
                        chartOpenValues[i] = chartValue;

                        chartValue = {data: vlClose}
                        chartCloseValues[i] = chartValue;

                        chartLabel = {data: new Date(dt).toLocaleDateString()}
                        chartLabels[i] = chartLabel;
                    }
                }

            }
                   
        }       


        this.setState({
            chartData: {
                labels: chartLabels.map(function(r) {
                    return r["data"];
                }),
                datasets:[
                    {
                        label: "Open",
                        fill: false,
                        lineTension: 0.1,
                        backgroundColor: "rgba(255,255,255,0.2)",
                        borderColor: "rgba(0,0,0,1)",
                        borderWidth: 2,
                        borderCapStyle: 'butt',
                        borderDash: [],
                        borderDashOffset: 0.0,
                        borderJoinStyle: 'miter',
                        pointBorderColor: 'rgba(75,192,192,1)',
                        pointBackgroundColor: '#fff',
                        pointBorderWidth: 1,
                        pointHoverRadius: 5,
                        pointHoverBackgroundColor: 'rgba(75,192,192,1)',
                        pointHoverBorderColor: 'rgba(220,220,220,1)',
                        pointHoverBorderWidth: 2,
                        pointRadius: 1,
                        pointHitRadius: 10,            
                        hoverBackgroundColor: "rgba(255,99,132,0.4)",
                        hoverBorderColor: "rgba(255,99,132,1)",            
                        data: chartOpenValues.map(function(r) {
                            return r["data"];
                        })
                    },
                    {
                        label: "Close",
                        fill: false,
                        lineTension: 0.1,
                        backgroundColor: "rgba(255,99,132,0.2)",
                        borderColor: "rgba(255,99,132,1)",
                        borderWidth: 2,
                        borderCapStyle: 'butt',
                        borderDash: [],
                        borderDashOffset: 0.0,
                        borderJoinStyle: 'miter',
                        pointBorderColor: 'rgba(75,192,192,1)',
                        pointBackgroundColor: '#fff',
                        pointBorderWidth: 1,
                        pointHoverRadius: 5,
                        pointHoverBackgroundColor: 'rgba(75,192,192,1)',
                        pointHoverBorderColor: 'rgba(220,220,220,1)',
                        pointHoverBorderWidth: 2,
                        pointRadius: 1,
                        pointHitRadius: 10,            
                        hoverBackgroundColor: "rgba(255,99,132,0.4)",
                        hoverBorderColor: "rgba(255,99,132,1)",            
                        data: chartCloseValues.map(function(r) {
                            return r["data"];
                        }),
                    }

                ]
            }
        });
        
    }

    render() {
        return (          
            <div >
                <Line                    
                    data={this.state.chartData}
                    options={{                    
                        title: {
                            display: true,
                        },
                        legend:{
                            display: true
                        }
                    }}
                />
                
                <button onClick={this.PrepareChartMatrix}>
                    Refresh
                </button>

            </div>
        )
    }

}

export default ChartJs;