import React from 'react'
import ReactDOM from 'react-dom'

import { scaleOrdinal } from 'd3-scale'
import { BarChart } from 'react-d3-components'
import { Dropdown } from 'semantic-ui-react'

import '../css/Divs.css';

class ChartsD3 extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      dataset: this.loadInitData(),
      grouped: true,
      width: 500,
    }

    this.labels = ['Low', 'High']

    this.colorScale = scaleOrdinal()
      .domain(this.labels)
      .range(['#1f77b4', '#aec7e8'
      ])

  }

  static defaultPropos = {
    dataSet1:[]
  } 

  getLegend = schema => {
    let legend = []
    for (let i = 0; i < schema.length; i++) {
      const label = schema[i].label
      legend.push(
        <li
          key={i}
          style={{
            background: this.colorScale(label),
            width: 10,
            marginTop: 5,
            listStyleType: 'none',
            whiteSpace: 'nowrap',
          }}>
          <span style={{ marginLeft: 15 }}>{label}</span>
        </li>,
      )
    }
    return legend
  }

  loadInitData= () => { 
    const dataset1 = {
      series: [
        {
          label: '',
          data: [0],
        },
        {
          label: '',
          data: [0],
        },
      ],
      xAxis: {
        data: [0],
      },

    } 

    return dataset1
  }

  loadData(dataset) {
    let xAxis = null
    if (dataset.xAxis !== undefined) {
      xAxis = dataset.xAxis.data
    } else {
      xAxis = []
      for (let i = 0; i < dataset.series[0].data.length; i++) {
        xAxis.push(i + 1)
      }
    }

    let total_data = []
    for (let i = 0; i < dataset.series.length; i++) {
      const series = dataset.series[i]
      let data_i = {}
      data_i['label'] = series.label
      let values = []

      for (let j = 0; j < series.data.length; j++) {
        let index = {}
        let xLabel = xAxis[j]

        if (xLabel === null) {
          xLabel = j
        }

        index['x'] = xLabel.toString()
        index['y'] = series.data[j]
        values.push(index)

      }

      let sort_values = values.sort(function(a, b) {
        return a.x - b.x
      })

      data_i['values'] = sort_values
      total_data.push(data_i)
    }    


    return total_data
  }

   getChart = (data, grouped, width) => {

    const chart = (data, width) => (
      <BarChart
        className = ".dropdown"
        groupedBars
        data={data}
        colorScale={this.colorScale}
        width={700}
        height={274}
        margin={{ top: 0, bottom: 30, left: 40, right: 0 }}
      />
    )    

    return chart(data, width)
  } 

  PrepareChartMatrix = () => {
       
    if (this.props.dataSet1.length === 0) {
      alert("Invalid date!")

      this.setState({
          chartData: []
      });

    } else {

      this.props.dataSet1.sort(function(a, b){
        var x = a.timestamp;
        var y = b.timestamp;

        if ((new Date(x)) < (new Date(y))) {return -1;}
        if ((new Date(x)) > (new Date(y))) {return 1;}
        return 0;
      });

      const dataset1 = {
        series: [
         {
            label: 'Low',
            data: this.props.dataSet1.map(function(r) {
              return  r["low"];
            }),
          },
          {
            label: 'High',
            data: this.props.dataSet1.map(function(r) {
              return r["high"];
            }),
          },
        ],
        xAxis: {
          data: this.props.dataSet1.map(function(r) {
            return new Date(r["timestamp"]).toLocaleDateString();
          }),
        },
        chart: {
          type: 'Scatter Plot',
          grouped: true,
        },

      } 

      this.setState({
        dataset: dataset1
      });

    }

  }

  render() {
    const { grouped, width } = this.state
    var data = this.loadData(this.state.dataset)
    var chart = this.getChart(data, grouped, width)
    var legend = this.getLegend(data);

    const options = ['Low', 'Open'];

    return (
      <div >
          <div className="dropdown">  
            <Dropdown
                selection
                fluid
                scrolling
                options={options}
            />
        
            <ul className="plot-legend">{legend}</ul>
            {chart}
            </div>

            <div>    
                <button onClick={this.PrepareChartMatrix}>
                    Refresh
                </button>
            </div>  

      </div>

    )
  }
}

export default ChartsD3
