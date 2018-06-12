import React, { Component } from 'react';
import LiteTable from './components/LiteTable';

export default class AlertPage extends Component {
  static displayName = 'AlertPage';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="alert-page-page">
        <LiteTable />
      </div>
    );
  }
}
