import http from 'k6/http';
import { sleep } from 'k6';
import d from 'k6/execution';

export const options = {
  iterations: 1000,
};

// The default exported function is gonna be picked up by k6 as the entry point for the test script. It will be executed repeatedly in "iterations" for the whole duration of the test.
export default function () {
  // Make a GET request to the target URL
  http.get('https://quickpizza.grafana.com');

  // Sleep for 1 second to simulate real-world usage
  sleep(1)
  console.log("print statement");
}