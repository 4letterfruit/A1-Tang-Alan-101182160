const apiBaseUrl = "http://localhost:8080";

async function test() {
    try {
        const response = await fetch(`${apiBaseUrl}/test`);
        const result = await response.text();
        console.log("Test Game Response:", result);
    } catch (error) {
        console.error("Error in test:", error);
    }
}

