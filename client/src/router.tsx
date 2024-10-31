import { createBrowserRouter } from "react-router-dom";
import { RootLayout } from "./layouts/RootLayout";
import { About } from "./pages/About";
import { ErrorPage } from "./pages/ErrorPage";
import { Mancala } from "./pages/Mancala";
import { Rules } from "./pages/Rules";
import { Start } from "./pages/Start";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <RootLayout />,
        errorElement: <ErrorPage />,
        children: [
            {
                index: true,
                element: <Mancala />
            },
            {
                path: "newGame",
                element: <Start startNewGame={true} />
            },
            {
                path: "rules",
                element: <Rules />
            },
            {
                path: "about",
                element: <About />
            }
        ]
    }
]);
