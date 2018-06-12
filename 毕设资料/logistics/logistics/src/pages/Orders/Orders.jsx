import React, { Component } from 'react';
import FilterTable from './components/FilterTable';

export default class Orders extends Component {
  static displayName = 'Orders';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="orders-page">
        <FilterTable />
      </div>
    );
  }
}
