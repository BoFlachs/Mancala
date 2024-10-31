import { GameState } from "../types";

export async function startGame(player1: string, player2: string) {
    const response = await fetch("mancala/api/start", {
        method: "POST",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            player1: player1,
            player2: player2,
        }),
    });

    if (response.ok) {
        const gameState = await response.json();
        return gameState as GameState;
    } else {
        return {
            statusCode: response.status,
            statusText: response.statusText
        };
    }
}

export async function playMove(indexToPlay: number) {
    const response = await fetch("mancala/api/play", {
        method: "POST",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            indexToPlay: indexToPlay,
        }),
    });

    if (response.ok) {
        const gameState = await response.json();
        return gameState as GameState;
    } else {
        return {
            statusCode: response.status,
            statusText: response.statusText
        };
    }
}

export async function getGame(){
    const response = await fetch("mancala/api/", {
        method: "GET",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
    });

    if (response.status==200) {
        const gameState = await response.json();
        return gameState as GameState;
    } 
    else {
        console.log(response)
        return undefined;
    }
}
