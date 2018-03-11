graph [
        comment "Nodes"
        node [
        		id A
        ]
        node [
        		id B
        ]
        node [
        		id C
        ]
        node [
        		id D
        ]
        node [
        		id E
        ]
        node [
        		id F
        ]
        node [
        		id G
        ]

        comment "Edges"
        edge [
        		source A
        		target B
        		weight 5
        ]
        edge [
        		source A
        		target C
        		weight 1
        ]
        edge [
        		source A
        		target D
        		weight 4
        ]
        edge [
        		source B 
        		target D
        		weight 8
        ]
        edge [
        		source B 
        		target F
        		weight 6
        ]
        edge [
        		source C 
        		target D
        		weight 3
        ]
        edge [
        		source C 
        		target E
        		weight 2
        ]
        edge [
        		source D 
        		target F
        		weight 8
        ]
        edge [
        		source E 
        		target F
        		weight 7
        ]
        edge [
        		source E 
        		target G
        		weight 9
        ]
]