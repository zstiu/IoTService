import React, { Component } from 'react';
import FilterTable from './components/FilterTable';

export default class Device extends Component {
  static displayName = 'Device';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="device-page">
        <FilterTable />
      </div>
    );
  }
}
