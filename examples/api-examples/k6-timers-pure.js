export default function () {
    const intervalId = setInterval(() => {
        console.log('This runs every 200ms');
    }, 200);

    const timeoutId = setTimeout(() => {
        console.log('This runs after 2s');

        // clear the timeout and interval to exit k6
        clearInterval(intervalId);
        clearTimeout(timeoutId);
    }, 2000);
}