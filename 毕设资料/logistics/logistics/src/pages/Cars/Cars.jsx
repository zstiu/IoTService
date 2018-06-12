import React, { Component } from 'react';
import FilterTable from './components/FilterTable';

export default class Cars extends Component {
  static displayName = 'Cars';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="cars-page">
        <FilterTable />
      </div>
    );
  }
}
