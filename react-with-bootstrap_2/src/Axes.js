import React from 'react'
import Axis from './components/Axis'

export default ({ scales1, margins, svgDimensions }) => {
  const { height, width } = svgDimensions

  const xProps = {
    orient: 'Bottom',
    scale: scales1.xScale,
    translate: `translate(0, ${height - margins.bottom})`,
    tickSize: height - margins.top - margins.bottom,
  }

  const yProps = {
    orient: 'Left',
    scale: scales1.yScale,
    translate: `translate(${margins.left}, 0)`,
    tickSize: width - margins.left - margins.right,
  }

  return (
    <g>
      <Axis {...xProps} />
      <Axis {...yProps} />
    </g>
  )
}